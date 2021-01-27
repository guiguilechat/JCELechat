package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_bids_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;

public class Contracts {

	public final ESIStatic esiConnection;

	public Contracts(ESIStatic esiConnection) {
		this.esiConnection = esiConnection;
	}

	public static class ContractDesc {

		public final R_get_contracts_public_region_id details;
		public final List<R_get_contracts_public_items_contract_id> items;
		public final List<R_get_contracts_public_bids_contract_id> bids;

		public ContractDesc(R_get_contracts_public_region_id details, List<R_get_contracts_public_items_contract_id> items,
				List<R_get_contracts_public_bids_contract_id> bids) {
			this.details = details;
			this.items = items;
			this.bids = bids;
		}

		@Override
		public String toString() {
			return "contract" + (details == null ? "null" : details.contract_id);
		}
	}

	private HashMap<Integer, ObsListHolder<ContractDesc>> cacheRegionContracts = new HashMap<>();

	/**
	 *
	 * get all the public contracts for a region. They are filled with the
	 * contract bids and items.
	 *
	 * @param regionId
	 *          id of the region.
	 * @returna cached synced variable
	 */
	public ObsListHolder<ContractDesc> getRegionContracts(int regionId) {
		ObsListHolder<ContractDesc> ret = cacheRegionContracts.get(regionId);
		if (ret == null) {
			ObsListHolder<R_get_contracts_public_region_id> raws = esiConnection.cache().contracts.getpublic(regionId);
			synchronized (cacheRegionContracts) {
				ret = cacheRegionContracts.get(regionId);
				if (ret == null) {
					ret = raws.peek(map -> {
						for (R_get_contracts_public_region_id cid : map) {
							ESIStatic.INSTANCE.cache().contracts.public_items(cid.contract_id);
							ESIStatic.INSTANCE.cache().contracts.public_bids(cid.contract_id);
						}
					}).mapItems(c -> new ContractDesc(c, ESIStatic.INSTANCE.cache().contracts.public_items(c.contract_id).get(),
							ESIStatic.INSTANCE.cache().contracts.public_bids(c.contract_id).get()));
					cacheRegionContracts.put(regionId, ret);
				}
			}
		}
		return ret;
	}

	private HashMap<Integer, ObsListHolder<ContractDesc>> cacheRegionItemExchanges = new HashMap<>();

	/**
	 * get the public item exchange contracts for a region.
	 *
	 * @param regionId
	 *          id of the region.
	 * @return a cached synced variable
	 */
	public ObsListHolder<ContractDesc> getRegionItemExchanges(int regionId) {
		ObsListHolder<ContractDesc> ret = cacheRegionItemExchanges.get(regionId);
		if (ret == null) {
			ObsListHolder<R_get_contracts_public_region_id> raws = esiConnection.cache().contracts.getpublic(regionId);
			synchronized (cacheRegionContracts) {
				ret = cacheRegionItemExchanges.get(regionId);
				if (ret == null) {
					ret = raws.filter(c -> c.type == get_contracts_public_region_id_type.item_exchange).peek(map -> {
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

	private HashMap<Integer, ObsListHolder<ContractDesc>> cacheRegionItemOffers = new HashMap<>();

	/**
	 * get the contracts that offer items and only require isks
	 *
	 * @param regionId
	 *          id of the region.
	 * @return a cached synced variable
	 */
	public ObsListHolder<ContractDesc> getRegionItemOffers(int regionId) {
		ObsListHolder<ContractDesc> ret = cacheRegionItemOffers.get(regionId);
		if (ret == null) {
			ObsListHolder<ContractDesc> offers = getRegionItemExchanges(regionId);
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
