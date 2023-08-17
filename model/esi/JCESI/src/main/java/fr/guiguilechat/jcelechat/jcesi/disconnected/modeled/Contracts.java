package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_bids_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Contracts {

	public final ESIStatic esiConnection;

	public Contracts(ESIStatic esiConnection) {
		this.esiConnection = esiConnection;
	}

	@Getter
	@AllArgsConstructor
	public static class ContractDesc {

		public final R_get_contracts_public_region_id details;
		public final List<R_get_contracts_public_items_contract_id> items;
		public final List<R_get_contracts_public_bids_contract_id> bids;


		@Override
		public String toString() {
			return "contract" + (details == null ? "null" : details.contract_id);
		}
	}

	@RequiredArgsConstructor
	public class RegionalContracts {

		@Getter
		private final int regionId;

		private final ListHolder<R_get_contracts_public_region_id> rawContracts;

		private final Map<Integer, ContractDesc> cachedContractsData = new HashMap<>();

		@Getter(lazy = true)
		private final ListHolder<ContractDesc> fullContracts = rawContracts.mapList(l -> {
			// remove all the cached items that are not needed anymore
			cachedContractsData.keySet()
			.retainAll(l.stream().mapToInt(c -> c.contract_id).boxed().collect(Collectors.toSet()));
			// then fetch and cache all missing contracts
			l.parallelStream().forEach(c -> {
				if (!cachedContractsData.containsKey(c.contract_id)) {
					ContractDesc data = new ContractDesc(c,
							ESIStatic.INSTANCE
							.requestGetPages(
											(page, props) -> ESIStatic.INSTANCE.get_contracts_public_items(c.contract_id, page, props),
									null)
							.getOK(),
							ESIStatic.INSTANCE
							.requestGetPages(
											(page, props) -> ESIStatic.INSTANCE.get_contracts_public_bids(c.contract_id, page, props),
									null)
							.getOK());
					synchronized (cachedContractsData) {
						cachedContractsData.put(c.contract_id, data);
					}
				}
			});
			return l.stream().map(c -> cachedContractsData.get(c.contract_id)).toList();
		});

		@Getter(lazy = true)
		private final ListHolder<ContractDesc> itemOffers = getFullContracts()
		.filter(cd -> cd.items.stream().filter(it -> !it.is_included).findAny().isEmpty());

	}

	private HashMap<Integer, RegionalContracts> cacheRegionalData = new HashMap<>();

	public RegionalContracts forRegion(int regionId) {
		synchronized (cacheRegionalData) {
			return cacheRegionalData.computeIfAbsent(regionId,
					rid -> new RegionalContracts(rid, esiConnection.cache().contracts.getpublic(rid)));
		}
	}

	private HashMap<Integer, ListHolder<ContractDesc>> cacheRegionContracts = new HashMap<>();

	/**
	 *
	 * get all the public contracts for a region. They are filled with the
	 * contract bids and items.
	 *
	 * @param regionId
	 *          id of the region.
	 * @returna cached synced variable
	 */
	public ListHolder<ContractDesc> getRegionContracts(int regionId) {
		ListHolder<ContractDesc> ret = cacheRegionContracts.get(regionId);
		if (ret == null) {
			ListHolder<R_get_contracts_public_region_id> raws = esiConnection.cache().contracts.getpublic(regionId);
			synchronized (cacheRegionContracts) {
				ret = cacheRegionContracts.get(regionId);
				if (ret == null) {
					ret = raws.follow(contractsList -> {
						for (R_get_contracts_public_region_id contract : contractsList) {
							ESIStatic.INSTANCE.cache().contracts.public_items(contract.contract_id);
							ESIStatic.INSTANCE.cache().contracts.public_bids(contract.contract_id);
						}
					}).mapItems(c -> new ContractDesc(c, ESIStatic.INSTANCE.cache().contracts.public_items(c.contract_id).get(),
							ESIStatic.INSTANCE.cache().contracts.public_bids(c.contract_id).get()));
					cacheRegionContracts.put(regionId, ret);
				}
			}
		}
		return ret;
	}

	private HashMap<Integer, ListHolder<ContractDesc>> cacheRegionItemExchanges = new HashMap<>();

	/**
	 * get the public item exchange contracts for a region.
	 *
	 * @param regionId
	 *          id of the region.
	 * @return a cached synced variable
	 */
	public ListHolder<ContractDesc> getRegionItemExchanges(int regionId) {
		ListHolder<ContractDesc> ret = cacheRegionItemExchanges.get(regionId);
		if (ret == null) {
			ListHolder<R_get_contracts_public_region_id> raws = esiConnection.cache().contracts.getpublic(regionId);
			synchronized (cacheRegionContracts) {
				ret = cacheRegionItemExchanges.get(regionId);
				if (ret == null) {
					ret = raws
							.filter(c -> c.type == get_contracts_public_region_id_type.item_exchange).follow(map -> {
								for (R_get_contracts_public_region_id cid : map) {
									ESIStatic.INSTANCE.cache().contracts.public_items(cid.contract_id);
								}
							}).mapItems(
									c -> new ContractDesc(c, ESIStatic.INSTANCE.cache().contracts.public_items(c.contract_id).get(), null));
					cacheRegionItemExchanges.put(regionId, ret);
				}
			}
		}
		return ret;
	}

	private HashMap<Integer, ListHolder<ContractDesc>> cacheRegionItemOffers = new HashMap<>();

	/**
	 * get the contracts that offer items and only require isks
	 *
	 * @param regionId
	 *          id of the region.
	 * @return a cached synced variable
	 */
	public ListHolder<ContractDesc> getRegionItemOffers(int regionId) {
		ListHolder<ContractDesc> ret = cacheRegionItemOffers.get(regionId);
		if (ret == null) {
			ListHolder<ContractDesc> offers = getRegionItemExchanges(regionId);
			synchronized (cacheRegionItemOffers) {
				ret = cacheRegionItemOffers.get(regionId);
				if (ret == null) {
					ret = offers.filter(cd -> cd.items.stream().filter(it -> !it.is_included).findAny().isEmpty());
					cacheRegionItemOffers.put(regionId, ret);
				}
			}
		}
		return ret;
	}

}
