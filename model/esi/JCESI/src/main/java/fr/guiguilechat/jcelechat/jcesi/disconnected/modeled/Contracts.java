package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_contracts_public_bids_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.impl.ObsListHolderImpl;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contracts {

	public final ESIStatic esiConnection;

	public Contracts(ESIStatic esiConnection) {
		this.esiConnection = esiConnection;
	}

	public static class ContractDesc {
		public R_get_contracts_public_region_id details = null;
		public List<R_get_contracts_public_items_contract_id> items = null;
		public List<R_get_contracts_public_bids_contract_id> bids = null;
	}

	private HashMap<Integer, ObsListHolder<ContractDesc>> caches = new HashMap<>();

	/** observe the contracts on given region. */
	public ObsListHolder<ContractDesc> get(int regionId) {
		ObsListHolder<ContractDesc> ret = caches.get(regionId);
		if (ret == null) {
			synchronized (caches) {
				ret = caches.get(regionId);
				if (ret == null) {
					ObservableList<ContractDesc> underlying = FXCollections.observableArrayList();
					ret = new ObsListHolderImpl<>(underlying);
					ObsListHolder<R_get_contracts_public_region_id> CHolder = esiConnection.cache.contracts.getpublic(regionId);
					CHolder.follow(c -> {
						while (c.next()) {
							// contracts can't be changed : only removed and added
							if (c.wasRemoved()) {
								for (R_get_contracts_public_region_id it : c.getRemoved()) {
									synchronized (underlying) {
										System.err.println("remove contract " + it.contract_id);
										underlying.removeIf(con -> con.details.contract_id == it.contract_id);
									}
								}
							}
							if (c.wasAdded()) {
								ArrayList<ContractFetcher> fetchers = new ArrayList<>();
								for (R_get_contracts_public_region_id it : c.getAddedSubList()) {
									System.err.println("new contract to fetch " + it.contract_id);
									ContractFetcher fetcher = new ContractFetcher(it, underlying);
									esiConnection.exec.schedule(fetcher, 0, TimeUnit.SECONDS);
									fetchers.add(fetcher);
								}
								System.err.println("one pass of contracts is scheduled to fetch");
								// once all fetchers have received data, the returned list is
								// notified the first pass is done.
								esiConnection.exec.schedule(() -> {
									for (ContractFetcher cf : fetchers) {
										cf.waitData();
									}
									CHolder.dataReceived();
									System.err.println("contract description complete");
								}, 0, TimeUnit.SECONDS);
							}
						}
					});
					caches.put(regionId, ret);
				}
			}
		}
		return ret;
	}

	/**
	 * fetch the data for a contract, then add the whole details to an underlying
	 * collection.
	 *
	 */
	private class ContractFetcher implements Runnable {

		R_get_contracts_public_region_id contract;

		Collection<ContractDesc> storage;

		public ContractFetcher(R_get_contracts_public_region_id contract, Collection<ContractDesc> storage) {
			this.contract = contract;
			this.storage = storage;
		}

		CountDownLatch waitData = new CountDownLatch(1);

		@Override
		public void run() {
			ContractDesc newdesc = new ContractDesc();
			newdesc.details = contract;
			newdesc.items = "auction".equals(contract.type) || "item_exchange".equals(contract.type)
					? ConnectedImpl.loadPages((p, h) -> esiConnection.get_contracts_public_items(contract.contract_id, p, h),
							null)
					: Collections.emptyList();
			newdesc.bids = "auction".equals(contract.type)
					? ConnectedImpl.loadPages((p, h) -> esiConnection.get_contracts_public_bids(contract.contract_id, p, h), null)
					: Collections.emptyList();
			System.err.println("details for contract " + contract.contract_id + " acquired, items:" + newdesc.items.size()
					+ " bids:" + newdesc.bids.size());

			synchronized (storage) {
				storage.add(newdesc);
			}
			waitData.countDown();
		}

		public void waitData() {
			try {
				waitData.await();
			} catch (InterruptedException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}

	}

}
