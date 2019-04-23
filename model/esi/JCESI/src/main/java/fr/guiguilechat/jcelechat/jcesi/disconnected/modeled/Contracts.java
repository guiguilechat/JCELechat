package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_bids_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import fr.lelouet.collectionholders.impl.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.ObsMapHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Contracts {

	public final ESIStatic esiConnection;

	public Contracts(ESIStatic esiConnection) {
		this.esiConnection = esiConnection;
	}

	public static class ContractDesc {
		public R_get_contracts_public_region_id details = null;
		public List<R_get_contracts_public_items_contract_id> items = null;
		public List<R_get_contracts_public_bids_contract_id> bids = null;

		@Override
		public String toString() {
			return "contract" + (details == null ? "null" : details.contract_id);
		}
	}

	private HashMap<Integer, ObsMapHolder<Integer, ContractDesc>> caches = new HashMap<>();

	/** observe the contracts on given region. */
	public ObsMapHolder<Integer, ContractDesc> getItemContracts(int regionId) {
		ObsMapHolder<Integer, ContractDesc> ret = caches.get(regionId);
		if (ret == null) {
			synchronized (caches) {
				ret = caches.get(regionId);
				if (ret == null) {
					ObservableMap<Integer, ContractDesc> underlying = FXCollections.observableHashMap();
					ret = new ObsMapHolderImpl<>(underlying);
					ObsListHolder<R_get_contracts_public_region_id> CHolder = esiConnection.cache.contracts.getpublic(regionId);
					CHolder.follow(change -> {
						Set<Integer> removedIndexes = new HashSet<>();
						List<R_get_contracts_public_region_id> added = new ArrayList<>();
						while (change.next()) {
							// contracts can't be changed : only removed and added
							if (change.wasRemoved()) {
								for (R_get_contracts_public_region_id it : change.getRemoved()) {
									removedIndexes.add(it.contract_id);
								}
							}
							if (change.wasAdded()) {
								for (int i = change.getFrom(); i < change.getTo(); i++) {
									R_get_contracts_public_region_id toadd = change.getList().get(i);
									if (get_contracts_public_region_id_type.item_exchange.equals(toadd.type) && toadd.price > 0) {
										added.add(toadd);
									}
								}
							}
						}
						for (R_get_contracts_public_region_id c : added) {
							removedIndexes.remove(c.contract_id);
						}
						List<ItemContractFetcher> tmplist = added.stream().map(ItemContractFetcher::new)
								.collect(Collectors.toList());
						Map<Integer, ContractDesc> newContracts = tmplist.stream().map(ItemContractFetcher::get)
								.collect(Collectors.toMap(o -> o.details.contract_id, o -> o));
						LockWatchDog.BARKER.tak(underlying);
						synchronized (underlying) {
							LockWatchDog.BARKER.hld(underlying);
							for (Integer i : removedIndexes) {
								underlying.remove(i);
							}
							underlying.putAll(newContracts);
						}
						LockWatchDog.BARKER.rel(underlying);
					});
					ObsMapHolder<Integer, ContractDesc> finalRet = ret;
					CHolder.addReceivedListener(c -> finalRet.dataReceived());
					caches.put(regionId, ret);
				}
			}
		}
		return ret;
	}

	/**
	 * fetch the data for a contract when run. the get() method schedules the
	 * run(), then wait for its completion.
	 *
	 */
	private class ItemContractFetcher {

		public ItemContractFetcher(R_get_contracts_public_region_id contract) {
			ret = new ContractDesc();
			ret.details = contract;
			holder = ESIStatic.INSTANCE.cache.contracts.public_items(contract.contract_id);
		}

		private ContractDesc ret;

		ObsListHolder<R_get_contracts_public_items_contract_id> holder;
		public ContractDesc get() {
			ret.items = holder.copy();
			return ret;
		}

	}

}
