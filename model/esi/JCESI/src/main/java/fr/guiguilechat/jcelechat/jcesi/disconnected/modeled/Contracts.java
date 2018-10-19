package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
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

		@Override
		public String toString() {
			return "contract" + (details == null ? "null" : details.contract_id);
		}
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
						Stream<R_get_contracts_public_region_id> stream = Stream.empty();
						while (c.next()) {
							// contracts can't be changed : only removed and added
							if (c.wasRemoved()) {
								for (R_get_contracts_public_region_id it : c.getRemoved()) {
									LockWatchDog.BARKER.tak(underlying);
									synchronized (underlying) {
										LockWatchDog.BARKER.hld(underlying);
										underlying.removeIf(con -> con.details.contract_id == it.contract_id);
									}
									LockWatchDog.BARKER.rel(underlying);
								}
							}
							if (c.wasAdded()) {
								stream = Stream.concat(stream, c.getAddedSubList().stream());
							}
						}
						List<ContractFetcher> tmplist = stream.map(ContractFetcher::new).collect(Collectors.toList());
						List<ContractDesc> newContracts = tmplist.stream().map(ContractFetcher::get)
								.collect(Collectors.toList());
						LockWatchDog.BARKER.tak(underlying);
						synchronized (underlying) {
							LockWatchDog.BARKER.hld(underlying);
							underlying.addAll(newContracts);
						}
						LockWatchDog.BARKER.rel(underlying);
					});
					caches.put(regionId, ret);
				}
			}
		}
		return ret;
	}

	/**
	 * fetch the data for a contract when run. the get() method schedules the
	 * run(), then wiatfor its completion.
	 *
	 */
	private class ContractFetcher implements Runnable {

		R_get_contracts_public_region_id contract;

		public ContractFetcher(R_get_contracts_public_region_id contract) {
			this.contract = contract;
			esiConnection.exec.submit(this);
		}

		CountDownLatch waitData = new CountDownLatch(1);

		private ContractDesc ret;

		@Override
		public void run() {
			ret = new ContractDesc();
			ret.details = contract;
			if ("auction".equals(contract.type) || "item_exchange".equals(contract.type)) {
				ret.items = ConnectedImpl
						.loadPages((p, h) -> esiConnection.get_contracts_public_items(contract.contract_id, p, h), null);
			} else {
				ret.items = Collections.emptyList();
			}
			if ("auction".equals(contract.type)) {
				ret.bids = ConnectedImpl
						.loadPages((p, h) -> esiConnection.get_contracts_public_bids(contract.contract_id, p, h), null);
			} else {
				ret.bids = Collections.emptyList();
			}
			waitData.countDown();
		}

		public ContractDesc get() {
			try {
				waitData.await();
			} catch (InterruptedException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
			return ret;
		}

	}

}
