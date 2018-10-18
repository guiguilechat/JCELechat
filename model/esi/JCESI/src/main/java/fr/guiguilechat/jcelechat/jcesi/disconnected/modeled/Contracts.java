package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
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
		R_get_contracts_public_region_id details;
		List<R_get_contracts_public_items_contract_id> items;
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
									underlying.removeIf(con -> con.details.contract_id == it.contract_id);
								}
							}
							if (c.wasAdded()) {
								for (R_get_contracts_public_region_id it : c.getAddedSubList()) {
									ContractDesc added = new ContractDesc();
									added.details = it;
									added.items = ConnectedImpl
											.loadPages((p, h) -> esiConnection.get_contracts_public_items(it.contract_id, p, h),
													null);
									underlying.add(added);
								}
							}
						}
					});
					caches.put(regionId, ret);
				}
			}
		}
		return ret;
	}

}
