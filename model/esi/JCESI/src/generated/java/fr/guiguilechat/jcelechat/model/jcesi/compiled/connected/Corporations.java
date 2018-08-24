package fr.guiguilechat.jcelechat.model.jcesi.compiled.connected;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.keys.K_10_int_long_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.keys.K_11_int_int_Long;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.keys.K_20_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.keys.K_8_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.keys.K_9_int_Boolean;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_assets_8;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_blueprints_8;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_contacts_labels_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_contracts_22;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_contracts_bids_contract_4;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_contracts_contract_items_6;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_containers_logs;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_customs_offices;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_divisions;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_fw_stats;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_medals;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_medals_issued;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_members_titles;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_membertracking;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_orders_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_roles_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_shareholders;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_starbases;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_starbases_starbase_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_structures;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_titles;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_wallets;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsMapHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import v2.io.swagger.models.Swagger;

public class Corporations {
	public final SwaggerCOCache<?> cache;
	private final Map<Integer, ObsListHolder<M_get_bookmarks_9>> get_corporations_corporation_id_bookmarks_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_bookmarks_folders>> get_corporations_corporation_id_bookmarks_folders_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<M_get_contacts_labels_2>> get_corporations_corporation_id_contacts_labels_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<M_get_contracts_22>> get_corporations_corporation_id_contracts_holder = new HashMap<>();
	private final Map<K_8_int_int, ObsMapHolder<Integer, M_get_contracts_bids_contract_4>> get_corporations_corporation_id_contracts_contract_id_bids_holder = new HashMap<>();
	private final Map<K_8_int_int, ObsMapHolder<Long, M_get_contracts_contract_items_6>> get_corporations_corporation_id_contracts_contract_id_items_holder = new HashMap<>();
	private final Map<Integer, ObsMapHolder<Long, R_get_corporations_corporation_id_customs_offices>> get_corporations_corporation_id_customs_offices_holder = new HashMap<>();
	private final Map<Integer, ObsObjHolder<R_get_corporations_corporation_id_divisions>> get_corporations_corporation_id_divisions_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_facilities>> get_corporations_corporation_id_facilities_holder = new HashMap<>();
	private final Map<Integer, ObsObjHolder<R_get_corporations_corporation_id_fw_stats>> get_corporations_corporation_id_fw_stats_holder = new HashMap<>();
	private final Map<K_9_int_Boolean, ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs>> get_corporations_corporation_id_industry_jobs_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<M_get_killmails_2>> get_corporations_corporation_id_killmails_recent_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_medals>> get_corporations_corporation_id_medals_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_medals_issued>> get_corporations_corporation_id_medals_issued_holder = new HashMap<>();
	private final Map<Integer, ObsObjHolder<Integer>> get_corporations_corporation_id_members_limit_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_members_titles>> get_corporations_corporation_id_members_titles_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_membertracking>> get_corporations_corporation_id_membertracking_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_roles>> get_corporations_corporation_id_roles_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_roles_history>> get_corporations_corporation_id_roles_history_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_shareholders>> get_corporations_corporation_id_shareholders_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<M_get_standings_3>> get_corporations_corporation_id_standings_holder = new HashMap<>();
	private final Map<Integer, ObsMapHolder<Long, R_get_corporations_corporation_id_starbases>> get_corporations_corporation_id_starbases_holder = new HashMap<>();
	private final Map<K_10_int_long_int, ObsObjHolder<R_get_corporations_corporation_id_starbases_starbase_id>> get_corporations_corporation_id_starbases_starbase_id_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_titles>> get_corporations_corporation_id_titles_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_wallets>> get_corporations_corporation_id_wallets_holder = new HashMap<>();
	private final Map<K_11_int_int_Long, ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_transactions>> get_corporations_corporation_id_wallets_division_transactions_holder = new HashMap<>();
	private final Map<Integer, ObsMapHolder<Long, M_get_blueprints_8>> get_corporations_corporation_id_blueprints_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_contacts>> get_corporations_corporation_id_contacts_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_containers_logs>> get_corporations_corporation_id_containers_logs_holder = new HashMap<>();
	private final Map<Integer, ObsMapHolder<Long, R_get_corporations_corporation_id_orders_history>> get_corporations_corporation_id_orders_history_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_structures>> get_corporations_corporation_id_structures_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<M_get_assets_8>> get_corporations_corporation_id_assets_holder = new HashMap<>();
	private final Map<Integer, ObsListHolder<Integer>> get_corporations_corporation_id_members_holder = new HashMap<>();
	private final Map<Integer, ObsMapHolder<Long, R_get_corporations_corporation_id_orders>> get_corporations_corporation_id_orders_holder = new HashMap<>();
	private final Map<K_20_int_int, ObsMapHolder<Long, M_get_journal_13>> get_corporations_corporation_id_wallets_division_journal_holder = new HashMap<>();

	public Corporations(SwaggerCOCache<?> parent) {
		cache = parent;
	}

	/**
	 * A list of your corporation's bookmarks
	 *
	 * cache over {@link Swagger#get_corporations_bookmarks}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<M_get_bookmarks_9> bookmarks(int corporation_id) {
		ObsListHolder<M_get_bookmarks_9> ret = get_corporations_corporation_id_bookmarks_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_bookmarks_holder);
			synchronized (get_corporations_corporation_id_bookmarks_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_bookmarks_holder);
				ret = get_corporations_corporation_id_bookmarks_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<M_get_bookmarks_9> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_bookmarks_holder.put(corporation_id, ret);
					ObsListHolder<M_get_bookmarks_9> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_bookmarks", (page, headerHandler) -> cache.swagger.get_corporations_bookmarks(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_bookmarks_holder);
		}
		return ret;
	}

	/**
	 * A list of your corporation's bookmark folders
	 *
	 * cache over {@link Swagger#get_corporations_bookmarks_folders}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_bookmarks_folders> bookmarks_folders(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_bookmarks_folders> ret = get_corporations_corporation_id_bookmarks_folders_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_bookmarks_folders_holder);
			synchronized (get_corporations_corporation_id_bookmarks_folders_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_bookmarks_folders_holder);
				ret = get_corporations_corporation_id_bookmarks_folders_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_bookmarks_folders> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_bookmarks_folders_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_bookmarks_folders> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_bookmarks_folders", (page, headerHandler) -> cache.swagger.get_corporations_bookmarks_folders(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_bookmarks_folders_holder);
		}
		return ret;
	}

	/**
	 * Return custom labels for a corporation's contacts
	 *
	 * cache over {@link Swagger#get_corporations_contacts_labels}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<M_get_contacts_labels_2> contacts_labels(int corporation_id) {
		ObsListHolder<M_get_contacts_labels_2> ret = get_corporations_corporation_id_contacts_labels_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_contacts_labels_holder);
			synchronized (get_corporations_corporation_id_contacts_labels_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_contacts_labels_holder);
				ret = get_corporations_corporation_id_contacts_labels_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<M_get_contacts_labels_2> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_contacts_labels_holder.put(corporation_id, ret);
					ObsListHolder<M_get_contacts_labels_2> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_contacts_labels", (page, headerHandler) -> cache.swagger.get_corporations_contacts_labels(corporation_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_contacts_labels_holder);
		}
		return ret;
	}

	/**
	 * Returns contracts available to a corporation, only if the corporation is issuer, acceptor or assignee. Only returns contracts no older than 30 days, or if the status is "in_progress".
	 *
	 * cache over {@link Swagger#get_corporations_contracts}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<M_get_contracts_22> contracts(int corporation_id) {
		ObsListHolder<M_get_contracts_22> ret = get_corporations_corporation_id_contracts_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_contracts_holder);
			synchronized (get_corporations_corporation_id_contracts_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_contracts_holder);
				ret = get_corporations_corporation_id_contracts_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<M_get_contracts_22> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_contracts_holder.put(corporation_id, ret);
					ObsListHolder<M_get_contracts_22> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_contracts", (page, headerHandler) -> cache.swagger.get_corporations_contracts(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_holder);
		}
		return ret;
	}

	/**
	 * Lists bids on a particular auction contract
	 *
	 * cache over {@link Swagger#get_corporations_contracts_bids}<br />
	 *
	 * @param contract_id
	 *     ID of a contract
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsMapHolder<Integer, M_get_contracts_bids_contract_4> contracts_bids(int contract_id, int corporation_id) {
		K_8_int_int param = new K_8_int_int(corporation_id, contract_id);
		ObsMapHolder<Integer, M_get_contracts_bids_contract_4> ret = get_corporations_corporation_id_contracts_contract_id_bids_holder.get(param);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_contracts_contract_id_bids_holder);
			synchronized (get_corporations_corporation_id_contracts_contract_id_bids_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_contracts_contract_id_bids_holder);
				ret = get_corporations_corporation_id_contracts_contract_id_bids_holder.get(param);
				if (ret == null) {
					ObservableMap<Integer, M_get_contracts_bids_contract_4> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_contracts_contract_id_bids_holder.put(param, ret);
					ObsMapHolder<Integer, M_get_contracts_bids_contract_4> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_contracts_contract_id_bids", (page, headerHandler) -> cache.swagger.get_corporations_contracts_bids(contract_id, corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Integer, M_get_contracts_bids_contract_4> newmap = new LinkedHashMap<>();
							for (M_get_contracts_bids_contract_4 val: arr) {
								newmap.put(val.bid_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_contract_id_bids_holder);
		}
		return ret;
	}

	/**
	 * Lists items of a particular contract
	 *
	 * cache over {@link Swagger#get_corporations_contracts_items}<br />
	 *
	 * @param contract_id
	 *     ID of a contract
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsMapHolder<Long, M_get_contracts_contract_items_6> contracts_items(int contract_id, int corporation_id) {
		K_8_int_int param = new K_8_int_int(corporation_id, contract_id);
		ObsMapHolder<Long, M_get_contracts_contract_items_6> ret = get_corporations_corporation_id_contracts_contract_id_items_holder.get(param);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_contracts_contract_id_items_holder);
			synchronized (get_corporations_corporation_id_contracts_contract_id_items_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_contracts_contract_id_items_holder);
				ret = get_corporations_corporation_id_contracts_contract_id_items_holder.get(param);
				if (ret == null) {
					ObservableMap<Long, M_get_contracts_contract_items_6> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_contracts_contract_id_items_holder.put(param, ret);
					ObsMapHolder<Long, M_get_contracts_contract_items_6> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_contracts_contract_id_items", (page, headerHandler) -> cache.swagger.get_corporations_contracts_items(contract_id, corporation_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Long, M_get_contracts_contract_items_6> newmap = new LinkedHashMap<>();
							for (M_get_contracts_contract_items_6 val: arr) {
								newmap.put(val.record_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_contract_id_items_holder);
		}
		return ret;
	}

	/**
	 * List customs offices owned by a corporation
	 *
	 * cache over {@link Swagger#get_corporations_customs_offices}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsMapHolder<Long, R_get_corporations_corporation_id_customs_offices> customs_offices(int corporation_id) {
		ObsMapHolder<Long, R_get_corporations_corporation_id_customs_offices> ret = get_corporations_corporation_id_customs_offices_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_customs_offices_holder);
			synchronized (get_corporations_corporation_id_customs_offices_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_customs_offices_holder);
				ret = get_corporations_corporation_id_customs_offices_holder.get(corporation_id);
				if (ret == null) {
					ObservableMap<Long, R_get_corporations_corporation_id_customs_offices> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_customs_offices_holder.put(corporation_id, ret);
					ObsMapHolder<Long, R_get_corporations_corporation_id_customs_offices> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_customs_offices", (page, headerHandler) -> cache.swagger.get_corporations_customs_offices(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Long, R_get_corporations_corporation_id_customs_offices> newmap = new LinkedHashMap<>();
							for (R_get_corporations_corporation_id_customs_offices val: arr) {
								newmap.put(val.office_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_customs_offices_holder);
		}
		return ret;
	}

	/**
	 * Return corporation hangar and wallet division names, only show if a division is not using the default name
	 *
	 * cache over {@link Swagger#get_corporations_divisions}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsObjHolder<R_get_corporations_corporation_id_divisions> divisions(int corporation_id) {
		ObsObjHolder<R_get_corporations_corporation_id_divisions> ret = get_corporations_corporation_id_divisions_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_divisions_holder);
			synchronized (get_corporations_corporation_id_divisions_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_divisions_holder);
				ret = get_corporations_corporation_id_divisions_holder.get(corporation_id);
				if (ret == null) {
					SimpleObjectProperty<R_get_corporations_corporation_id_divisions> holder = new SimpleObjectProperty<>();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_divisions_holder.put(corporation_id, ret);
					cache.addFetchCacheObject("get_corporations_corporation_id_divisions", headerHandler -> cache.swagger.get_corporations_divisions(corporation_id, headerHandler), item -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.set(item);
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_divisions_holder);
		}
		return ret;
	}

	/**
	 * Return a corporation's facilities
	 *
	 * cache over {@link Swagger#get_corporations_facilities}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_facilities> facilities(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_facilities> ret = get_corporations_corporation_id_facilities_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_facilities_holder);
			synchronized (get_corporations_corporation_id_facilities_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_facilities_holder);
				ret = get_corporations_corporation_id_facilities_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_facilities> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_facilities_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_facilities> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_facilities", (page, headerHandler) -> cache.swagger.get_corporations_facilities(corporation_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Factory_Manager"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_facilities_holder);
		}
		return ret;
	}

	/**
	 * Statistics about a corporation involved in faction warfare
	 *
	 * cache over {@link Swagger#get_corporations_fw_stats}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsObjHolder<R_get_corporations_corporation_id_fw_stats> fw_stats(int corporation_id) {
		ObsObjHolder<R_get_corporations_corporation_id_fw_stats> ret = get_corporations_corporation_id_fw_stats_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_fw_stats_holder);
			synchronized (get_corporations_corporation_id_fw_stats_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_fw_stats_holder);
				ret = get_corporations_corporation_id_fw_stats_holder.get(corporation_id);
				if (ret == null) {
					SimpleObjectProperty<R_get_corporations_corporation_id_fw_stats> holder = new SimpleObjectProperty<>();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_fw_stats_holder.put(corporation_id, ret);
					cache.addFetchCacheObject("get_corporations_corporation_id_fw_stats", headerHandler -> cache.swagger.get_corporations_fw_stats(corporation_id, headerHandler), item -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.set(item);
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_fw_stats_holder);
		}
		return ret;
	}

	/**
	 * List industry jobs run by a corporation
	 *
	 * cache over {@link Swagger#get_corporations_industry_jobs}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 * @param include_completed
	 *     Whether to retrieve completed corporation industry jobs. Only includes jobs from the past 90 days
	 */
	public ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> industry_jobs(int corporation_id, Boolean include_completed) {
		K_9_int_Boolean param = new K_9_int_Boolean(corporation_id, include_completed);
		ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> ret = get_corporations_corporation_id_industry_jobs_holder.get(param);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_industry_jobs_holder);
			synchronized (get_corporations_corporation_id_industry_jobs_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_industry_jobs_holder);
				ret = get_corporations_corporation_id_industry_jobs_holder.get(param);
				if (ret == null) {
					ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_industry_jobs_holder.put(param, ret);
					ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_industry_jobs", (page, headerHandler) -> cache.swagger.get_corporations_industry_jobs(corporation_id, include_completed, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Integer, R_get_corporations_corporation_id_industry_jobs> newmap = new LinkedHashMap<>();
							for (R_get_corporations_corporation_id_industry_jobs val: arr) {
								newmap.put(val.job_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							, new String[] { "Factory_Manager" });
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_industry_jobs_holder);
		}
		return ret;
	}

	/**
	 * Get a list of a corporation's kills and losses going back 90 days
	 *
	 * cache over {@link Swagger#get_corporations_killmails_recent}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<M_get_killmails_2> killmails_recent(int corporation_id) {
		ObsListHolder<M_get_killmails_2> ret = get_corporations_corporation_id_killmails_recent_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_killmails_recent_holder);
			synchronized (get_corporations_corporation_id_killmails_recent_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_killmails_recent_holder);
				ret = get_corporations_corporation_id_killmails_recent_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<M_get_killmails_2> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_killmails_recent_holder.put(corporation_id, ret);
					ObsListHolder<M_get_killmails_2> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_killmails_recent", (page, headerHandler) -> cache.swagger.get_corporations_killmails_recent(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_killmails_recent_holder);
		}
		return ret;
	}

	/**
	 * Returns a corporation's medals
	 *
	 * cache over {@link Swagger#get_corporations_medals}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_medals> medals(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_medals> ret = get_corporations_corporation_id_medals_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_medals_holder);
			synchronized (get_corporations_corporation_id_medals_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_medals_holder);
				ret = get_corporations_corporation_id_medals_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_medals> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_medals_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_medals> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_medals", (page, headerHandler) -> cache.swagger.get_corporations_medals(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_medals_holder);
		}
		return ret;
	}

	/**
	 * Returns medals issued by a corporation
	 *
	 * cache over {@link Swagger#get_corporations_medals_issued}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_medals_issued> medals_issued(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_medals_issued> ret = get_corporations_corporation_id_medals_issued_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_medals_issued_holder);
			synchronized (get_corporations_corporation_id_medals_issued_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_medals_issued_holder);
				ret = get_corporations_corporation_id_medals_issued_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_medals_issued> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_medals_issued_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_medals_issued> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_medals_issued", (page, headerHandler) -> cache.swagger.get_corporations_medals_issued(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_medals_issued_holder);
		}
		return ret;
	}

	/**
	 * Return a corporation's member limit, not including CEO himself
	 *
	 * cache over {@link Swagger#get_corporations_members_limit}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsObjHolder<Integer> members_limit(int corporation_id) {
		ObsObjHolder<Integer> ret = get_corporations_corporation_id_members_limit_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_members_limit_holder);
			synchronized (get_corporations_corporation_id_members_limit_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_members_limit_holder);
				ret = get_corporations_corporation_id_members_limit_holder.get(corporation_id);
				if (ret == null) {
					SimpleObjectProperty<Integer> holder = new SimpleObjectProperty<>();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_members_limit_holder.put(corporation_id, ret);
					cache.addFetchCacheObject("get_corporations_corporation_id_members_limit", headerHandler -> cache.swagger.get_corporations_members_limit(corporation_id, headerHandler), item -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.set(item);
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_limit_holder);
		}
		return ret;
	}

	/**
	 * Returns a corporation's members' titles
	 *
	 * cache over {@link Swagger#get_corporations_members_titles}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_members_titles> members_titles(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_members_titles> ret = get_corporations_corporation_id_members_titles_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_members_titles_holder);
			synchronized (get_corporations_corporation_id_members_titles_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_members_titles_holder);
				ret = get_corporations_corporation_id_members_titles_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_members_titles> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_members_titles_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_members_titles> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_members_titles", (page, headerHandler) -> cache.swagger.get_corporations_members_titles(corporation_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_titles_holder);
		}
		return ret;
	}

	/**
	 * Returns additional information about a corporation's members which helps tracking their activities
	 *
	 * cache over {@link Swagger#get_corporations_membertracking}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_membertracking> membertracking(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_membertracking> ret = get_corporations_corporation_id_membertracking_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_membertracking_holder);
			synchronized (get_corporations_corporation_id_membertracking_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_membertracking_holder);
				ret = get_corporations_corporation_id_membertracking_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_membertracking> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_membertracking_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_membertracking> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_membertracking", (page, headerHandler) -> cache.swagger.get_corporations_membertracking(corporation_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_membertracking_holder);
		}
		return ret;
	}

	/**
	 * Return the roles of all members if the character has the personnel manager role or any grantable role.
	 *
	 * cache over {@link Swagger#get_corporations_roles}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_roles> roles(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_roles> ret = get_corporations_corporation_id_roles_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_roles_holder);
			synchronized (get_corporations_corporation_id_roles_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_roles_holder);
				ret = get_corporations_corporation_id_roles_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_roles> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_roles_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_roles> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_roles", (page, headerHandler) -> cache.swagger.get_corporations_roles(corporation_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_roles_holder);
		}
		return ret;
	}

	/**
	 * Return how roles have changed for a coporation's members, up to a month
	 *
	 * cache over {@link Swagger#get_corporations_roles_history}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_roles_history> roles_history(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_roles_history> ret = get_corporations_corporation_id_roles_history_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_roles_history_holder);
			synchronized (get_corporations_corporation_id_roles_history_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_roles_history_holder);
				ret = get_corporations_corporation_id_roles_history_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_roles_history> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_roles_history_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_roles_history> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_roles_history", (page, headerHandler) -> cache.swagger.get_corporations_roles_history(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_roles_history_holder);
		}
		return ret;
	}

	/**
	 * Return the current shareholders of a corporation.
	 *
	 * cache over {@link Swagger#get_corporations_shareholders}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_shareholders> shareholders(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_shareholders> ret = get_corporations_corporation_id_shareholders_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_shareholders_holder);
			synchronized (get_corporations_corporation_id_shareholders_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_shareholders_holder);
				ret = get_corporations_corporation_id_shareholders_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_shareholders> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_shareholders_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_shareholders> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_shareholders", (page, headerHandler) -> cache.swagger.get_corporations_shareholders(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_shareholders_holder);
		}
		return ret;
	}

	/**
	 * Return corporation standings from agents, NPC corporations, and factions
	 *
	 * cache over {@link Swagger#get_corporations_standings}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<M_get_standings_3> standings(int corporation_id) {
		ObsListHolder<M_get_standings_3> ret = get_corporations_corporation_id_standings_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_standings_holder);
			synchronized (get_corporations_corporation_id_standings_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_standings_holder);
				ret = get_corporations_corporation_id_standings_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<M_get_standings_3> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_standings_holder.put(corporation_id, ret);
					ObsListHolder<M_get_standings_3> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_standings", (page, headerHandler) -> cache.swagger.get_corporations_standings(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_standings_holder);
		}
		return ret;
	}

	/**
	 * Returns list of corporation starbases (POSes)
	 *
	 * cache over {@link Swagger#get_corporations_starbases}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsMapHolder<Long, R_get_corporations_corporation_id_starbases> starbases(int corporation_id) {
		ObsMapHolder<Long, R_get_corporations_corporation_id_starbases> ret = get_corporations_corporation_id_starbases_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_starbases_holder);
			synchronized (get_corporations_corporation_id_starbases_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_starbases_holder);
				ret = get_corporations_corporation_id_starbases_holder.get(corporation_id);
				if (ret == null) {
					ObservableMap<Long, R_get_corporations_corporation_id_starbases> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_starbases_holder.put(corporation_id, ret);
					ObsMapHolder<Long, R_get_corporations_corporation_id_starbases> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_starbases", (page, headerHandler) -> cache.swagger.get_corporations_starbases(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Long, R_get_corporations_corporation_id_starbases> newmap = new LinkedHashMap<>();
							for (R_get_corporations_corporation_id_starbases val: arr) {
								newmap.put(val.starbase_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_starbases_holder);
		}
		return ret;
	}

	/**
	 * Returns various settings and fuels of a starbase (POS)
	 *
	 * cache over {@link Swagger#get_corporations_starbases}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 * @param starbase_id
	 *     An EVE starbase (POS) ID
	 * @param system_id
	 *     The solar system this starbase (POS) is located in,
	 */
	public ObsObjHolder<R_get_corporations_corporation_id_starbases_starbase_id> starbases(int corporation_id, long starbase_id, int system_id) {
		K_10_int_long_int param = new K_10_int_long_int(corporation_id, starbase_id, system_id);
		ObsObjHolder<R_get_corporations_corporation_id_starbases_starbase_id> ret = get_corporations_corporation_id_starbases_starbase_id_holder.get(param);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_starbases_starbase_id_holder);
			synchronized (get_corporations_corporation_id_starbases_starbase_id_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_starbases_starbase_id_holder);
				ret = get_corporations_corporation_id_starbases_starbase_id_holder.get(param);
				if (ret == null) {
					SimpleObjectProperty<R_get_corporations_corporation_id_starbases_starbase_id> holder = new SimpleObjectProperty<>();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_starbases_starbase_id_holder.put(param, ret);
					cache.addFetchCacheObject("get_corporations_corporation_id_starbases_starbase_id", headerHandler -> cache.swagger.get_corporations_starbases(corporation_id, starbase_id, system_id, headerHandler), item -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.set(item);
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_starbases_starbase_id_holder);
		}
		return ret;
	}

	/**
	 * Returns a corporation's titles
	 *
	 * cache over {@link Swagger#get_corporations_titles}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_titles> titles(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_titles> ret = get_corporations_corporation_id_titles_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_titles_holder);
			synchronized (get_corporations_corporation_id_titles_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_titles_holder);
				ret = get_corporations_corporation_id_titles_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_titles> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_titles_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_titles> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_titles", (page, headerHandler) -> cache.swagger.get_corporations_titles(corporation_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_titles_holder);
		}
		return ret;
	}

	/**
	 * Get a corporation's wallets
	 *
	 * cache over {@link Swagger#get_corporations_wallets}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_wallets> wallets(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_wallets> ret = get_corporations_corporation_id_wallets_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_wallets_holder);
			synchronized (get_corporations_corporation_id_wallets_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_wallets_holder);
				ret = get_corporations_corporation_id_wallets_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_wallets> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_wallets_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_wallets> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_wallets", (page, headerHandler) -> cache.swagger.get_corporations_wallets(corporation_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Accountant", "Junior_Accountant"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_holder);
		}
		return ret;
	}

	/**
	 * Get wallet transactions of a corporation
	 *
	 * cache over {@link Swagger#get_corporations_wallets_transactions}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 * @param division
	 *     Wallet key of the division to fetch journals from
	 * @param from_id
	 *     Only show journal entries happened before the transaction referenced by this id
	 */
	public ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_transactions> wallets_transactions(int corporation_id, int division, Long from_id) {
		K_11_int_int_Long param = new K_11_int_int_Long(division, corporation_id, from_id);
		ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_transactions> ret = get_corporations_corporation_id_wallets_division_transactions_holder.get(param);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_wallets_division_transactions_holder);
			synchronized (get_corporations_corporation_id_wallets_division_transactions_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_wallets_division_transactions_holder);
				ret = get_corporations_corporation_id_wallets_division_transactions_holder.get(param);
				if (ret == null) {
					ObservableMap<Long, R_get_corporations_corporation_id_wallets_division_transactions> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_wallets_division_transactions_holder.put(param, ret);
					ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_transactions> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_wallets_division_transactions", (page, headerHandler) -> cache.swagger.get_corporations_wallets_transactions(corporation_id, division, from_id, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Long, R_get_corporations_corporation_id_wallets_division_transactions> newmap = new LinkedHashMap<>();
							for (R_get_corporations_corporation_id_wallets_division_transactions val: arr) {
								newmap.put(val.transaction_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Accountant", "Junior_Accountant"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_division_transactions_holder);
		}
		return ret;
	}

	/**
	 * Returns a list of blueprints the corporation owns
	 *
	 * cache over {@link Swagger#get_corporations_blueprints}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsMapHolder<Long, M_get_blueprints_8> blueprints(int corporation_id) {
		ObsMapHolder<Long, M_get_blueprints_8> ret = get_corporations_corporation_id_blueprints_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_blueprints_holder);
			synchronized (get_corporations_corporation_id_blueprints_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_blueprints_holder);
				ret = get_corporations_corporation_id_blueprints_holder.get(corporation_id);
				if (ret == null) {
					ObservableMap<Long, M_get_blueprints_8> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_blueprints_holder.put(corporation_id, ret);
					ObsMapHolder<Long, M_get_blueprints_8> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_blueprints", (page, headerHandler) -> cache.swagger.get_corporations_blueprints(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Long, M_get_blueprints_8> newmap = new LinkedHashMap<>();
							for (M_get_blueprints_8 val: arr) {
								newmap.put(val.item_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_blueprints_holder);
		}
		return ret;
	}

	/**
	 * Return contacts of a corporation
	 *
	 * cache over {@link Swagger#get_corporations_contacts}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_contacts> contacts(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_contacts> ret = get_corporations_corporation_id_contacts_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_contacts_holder);
			synchronized (get_corporations_corporation_id_contacts_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_contacts_holder);
				ret = get_corporations_corporation_id_contacts_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_contacts> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_contacts_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_contacts> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_contacts", (page, headerHandler) -> cache.swagger.get_corporations_contacts(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_contacts_holder);
		}
		return ret;
	}

	/**
	 * Returns logs recorded in the past seven days from all audit log secure containers (ALSC) owned by a given corporation
	 *
	 * cache over {@link Swagger#get_corporations_containers_logs}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_containers_logs> containers_logs(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_containers_logs> ret = get_corporations_corporation_id_containers_logs_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_containers_logs_holder);
			synchronized (get_corporations_corporation_id_containers_logs_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_containers_logs_holder);
				ret = get_corporations_corporation_id_containers_logs_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_containers_logs> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_containers_logs_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_containers_logs> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_containers_logs", (page, headerHandler) -> cache.swagger.get_corporations_containers_logs(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_containers_logs_holder);
		}
		return ret;
	}

	/**
	 * List cancelled and expired market orders placed on behalf of a corporation up to 90 days in the past.
	 *
	 * cache over {@link Swagger#get_corporations_orders_history}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsMapHolder<Long, R_get_corporations_corporation_id_orders_history> orders_history(int corporation_id) {
		ObsMapHolder<Long, R_get_corporations_corporation_id_orders_history> ret = get_corporations_corporation_id_orders_history_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_orders_history_holder);
			synchronized (get_corporations_corporation_id_orders_history_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_orders_history_holder);
				ret = get_corporations_corporation_id_orders_history_holder.get(corporation_id);
				if (ret == null) {
					ObservableMap<Long, R_get_corporations_corporation_id_orders_history> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_orders_history_holder.put(corporation_id, ret);
					ObsMapHolder<Long, R_get_corporations_corporation_id_orders_history> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_orders_history", (page, headerHandler) -> cache.swagger.get_corporations_orders_history(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Long, R_get_corporations_corporation_id_orders_history> newmap = new LinkedHashMap<>();
							for (R_get_corporations_corporation_id_orders_history val: arr) {
								newmap.put(val.order_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Accountant", "Trader"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_orders_history_holder);
		}
		return ret;
	}

	/**
	 * Get a list of corporation structures. This route's version includes the changes to structures detailed in this blog: https://www.eveonline.com/article/upwell-2.0-structures-changes-coming-on-february-13th
	 *
	 * cache over {@link Swagger#get_corporations_structures}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<R_get_corporations_corporation_id_structures> structures(int corporation_id) {
		ObsListHolder<R_get_corporations_corporation_id_structures> ret = get_corporations_corporation_id_structures_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_structures_holder);
			synchronized (get_corporations_corporation_id_structures_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_structures_holder);
				ret = get_corporations_corporation_id_structures_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<R_get_corporations_corporation_id_structures> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_structures_holder.put(corporation_id, ret);
					ObsListHolder<R_get_corporations_corporation_id_structures> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_structures", (page, headerHandler) -> cache.swagger.get_corporations_structures(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"StationManager"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_structures_holder);
		}
		return ret;
	}

	/**
	 * Return a list of the corporation assets
	 *
	 * cache over {@link Swagger#get_corporations_assets}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<M_get_assets_8> assets(int corporation_id) {
		ObsListHolder<M_get_assets_8> ret = get_corporations_corporation_id_assets_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_assets_holder);
			synchronized (get_corporations_corporation_id_assets_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_assets_holder);
				ret = get_corporations_corporation_id_assets_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<M_get_assets_8> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_assets_holder.put(corporation_id, ret);
					ObsListHolder<M_get_assets_8> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_assets", (page, headerHandler) -> cache.swagger.get_corporations_assets(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Director"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_assets_holder);
		}
		return ret;
	}

	/**
	 * Return the current member list of a corporation, the token's character need to be a member of the corporation.
	 *
	 * cache over {@link Swagger#get_corporations_members}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsListHolder<Integer> members(int corporation_id) {
		ObsListHolder<Integer> ret = get_corporations_corporation_id_members_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_members_holder);
			synchronized (get_corporations_corporation_id_members_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_members_holder);
				ret = get_corporations_corporation_id_members_holder.get(corporation_id);
				if (ret == null) {
					ObservableList<Integer> holder = FXCollections.observableArrayList();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_members_holder.put(corporation_id, ret);
					ObsListHolder<Integer> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_members", (page, headerHandler) -> IntStream.of(cache.swagger.get_corporations_members(corporation_id, headerHandler)).mapToObj(Integer::valueOf).toArray(Integer[]::new), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							holder.setAll(arr);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
							);
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_holder);
		}
		return ret;
	}

	/**
	 * List open market orders placed on behalf of a corporation
	 *
	 * cache over {@link Swagger#get_corporations_orders}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 */
	public ObsMapHolder<Long, R_get_corporations_corporation_id_orders> orders(int corporation_id) {
		ObsMapHolder<Long, R_get_corporations_corporation_id_orders> ret = get_corporations_corporation_id_orders_holder.get(corporation_id);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_orders_holder);
			synchronized (get_corporations_corporation_id_orders_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_orders_holder);
				ret = get_corporations_corporation_id_orders_holder.get(corporation_id);
				if (ret == null) {
					ObservableMap<Long, R_get_corporations_corporation_id_orders> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_orders_holder.put(corporation_id, ret);
					ObsMapHolder<Long, R_get_corporations_corporation_id_orders> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_orders", (page, headerHandler) -> cache.swagger.get_corporations_orders(corporation_id, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Long, R_get_corporations_corporation_id_orders> newmap = new LinkedHashMap<>();
							for (R_get_corporations_corporation_id_orders val: arr) {
								newmap.put(val.order_id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Accountant", "Trader"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_orders_holder);
		}
		return ret;
	}

	/**
	 * Retrieve the given corporation's wallet journal for the given division going 30 days back
	 *
	 * cache over {@link Swagger#get_corporations_wallets_journal}<br />
	 *
	 * @param corporation_id
	 *     An EVE corporation ID
	 * @param division
	 *     Wallet key of the division to fetch journals from
	 */
	public ObsMapHolder<Long, M_get_journal_13> wallets_journal(int corporation_id, int division) {
		K_20_int_int param = new K_20_int_int(division, corporation_id);
		ObsMapHolder<Long, M_get_journal_13> ret = get_corporations_corporation_id_wallets_division_journal_holder.get(param);
		if (ret == null) {
			LockWatchDog.BARKER.tak(get_corporations_corporation_id_wallets_division_journal_holder);
			synchronized (get_corporations_corporation_id_wallets_division_journal_holder)
			{
				LockWatchDog.BARKER.hld(get_corporations_corporation_id_wallets_division_journal_holder);
				ret = get_corporations_corporation_id_wallets_division_journal_holder.get(param);
				if (ret == null) {
					ObservableMap<Long, M_get_journal_13> holder = FXCollections.observableHashMap();
					ret = cache.toHolder(holder);
					get_corporations_corporation_id_wallets_division_journal_holder.put(param, ret);
					ObsMapHolder<Long, M_get_journal_13> finalRet = ret;
					cache.addFetchCacheArray("get_corporations_corporation_id_wallets_division_journal", (page, headerHandler) -> cache.swagger.get_corporations_wallets_journal(corporation_id, division, page, headerHandler), arr -> {
						LockWatchDog.BARKER.tak(holder);
						synchronized (holder)
						{
							LockWatchDog.BARKER.hld(holder);
							LinkedHashMap<Long, M_get_journal_13> newmap = new LinkedHashMap<>();
							for (M_get_journal_13 val: arr) {
								newmap.put(val.id, val);
							}
							holder.entrySet().retainAll(newmap.entrySet());
							holder.putAll(newmap);
							finalRet.dataReceived();
						}
						LockWatchDog.BARKER.rel(holder);
					}
					, new String[] {"Accountant", "Junior_Accountant"});
				}
			}
			LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_division_journal_holder);
		}
		return ret;
	}
}
