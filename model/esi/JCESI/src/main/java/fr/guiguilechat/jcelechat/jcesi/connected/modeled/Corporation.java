package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.CorpAssetFolder;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.CorpBookmarks;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.SystemAsset;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_divisions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_structures;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.lelouet.collectionholders.impl.ObsObjHolderImpl;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Corporation {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Corporation.class);

	protected final ESIAccount con;

	public Corporation(ESIAccount con) {
		this.con = con;
		bms = new CorpBookmarks(con);
	}

	public int getId() {
		return con.character.infos.corporationId().get();
	}

	public final CorpBookmarks bms;

	// industry jobs

	public ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> getIndustryJobs() {
		return ObsMapHolderImpl.toMap(con.raw.cache.corporations.industry_jobs(getId(), false), j -> j.job_id);
	}

	public static boolean isManufacture(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 1;
	}

	public static boolean isTE(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 3;
	}

	public static boolean isME(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 4;
	}

	public static boolean isCopy(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 5;
	}

	public static boolean isInvetion(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 8;
	}

	//
	// assets
	//

	public ObsListHolder<R_get_corporations_corporation_id_assets> getAssetsList() {
		return con.raw.cache.corporations.assets(getId());
	}

	public Map<String, SystemAsset> getAssetTree() {
		List<R_get_corporations_corporation_id_assets> assets = getAssetsList().get();
		Map<String, SystemAsset> ret = new HashMap<>();
		Map<Long, CorpAssetFolder> idToFolder = assets.stream()
				.collect(Collectors.toMap(asset -> asset.item_id, asset -> new CorpAssetFolder(asset)));
		for (CorpAssetFolder folder : idToFolder.values()) {
			CorpAssetFolder parent = idToFolder.get(folder.asset.location_id);
			if (parent != null) {
				parent.addAsset(folder);
			} else {
				Location resolved = Location.resolve(con, folder.asset.location_id);
				SystemAsset sysasset = null;
				switch (resolved.type) {
				case SYSTEM:
					// the location_id is a system :
					// this is our structure
					sysasset = getSystemAsset(ret, resolved.name);
					sysasset.owned.put(Location.resolve(con, folder.asset.item_id).name, folder);
					break;
				case STRUCTURE:
					// location_id is a structure, that we don't own
					if (resolved.ref == null) {
						// we can't resolved the structure. it's in system "unresolved"
						sysasset = getSystemAsset(ret, "unresolved");
					} else {
						// structure is resolved. get its system.
						sysasset = getSystemAsset(ret,
								Location.resolve(con, ((R_get_universe_structures_structure_id) resolved.ref).solar_system_id).name);
					}
					sysasset.rent.put(resolved.name, folder);
					break;
				case STATION:
					// this is a hangar we rent in a station
					R_get_universe_stations_station_id station = (R_get_universe_stations_station_id) resolved.ref;
					sysasset = getSystemAsset(ret, Location.resolve(con, station.system_id).name);
					sysasset.rent.put(resolved.name, folder);
					break;
				default:
					throw new UnsupportedOperationException("case not handled " + resolved.type);
				}
			}
		}
		return ret;
	}

	protected static SystemAsset getSystemAsset(Map<String, SystemAsset> data, String name) {
		SystemAsset ret = data.get(name);
		if (ret == null) {
			ret = new SystemAsset();
			data.put(name, ret);
		}
		return ret;
	}

	/**
	 *
	 * @return the location->typeid->quantity
	 */
	public ObservableMap<Long, ObservableMap<Integer, Integer>> getAssets() {
		ObservableMap<Long, ObservableMap<Integer, Integer>> assets = FXCollections.observableMap(new LinkedHashMap<>());
		R_get_corporations_corporation_id_assets[] itemsArr = con.raw.cache.corporations.assets(getId()).get().stream()
				.filter(asset -> !get_corporations_corporation_id_assets_location_flag.AutoFit.equals(asset.location_flag))
				.toArray(R_get_corporations_corporation_id_assets[]::new);
		try {
			// we make the map of itemid->locations. if a location is actually an
			// asset, we iterally map it to this asset's location instead
			Map<Long, Long> baseLocationMap = Stream.of(itemsArr)
					.collect(Collectors.toMap(i -> i.item_id, i -> i.location_id, (a, b) -> {
						Arrays.sort(itemsArr, (o1, o2) -> (int) Math.signum(o1.item_id - o2.item_id));
						logger.warn("double item location when fetching corporation assets " + a + ";" + b);
						for (R_get_corporations_corporation_id_assets asset : itemsArr) {
							logger.warn("" + asset.item_id + " : loc=" + asset.location_id + " type=" + asset.type_id + " x"
									+ asset.quantity);
						}
						return b;
					}));
			Map<Long, Long> idToLocation = baseLocationMap.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> {
				Long ret = e.getValue();
				while (baseLocationMap.containsKey(ret)) {
					ret = baseLocationMap.get(ret);
				}
				return ret;
			}));
			Map<Long, Map<Integer, Integer>> newitems = Stream.of(itemsArr)
					.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), Corporation::makeMap, Corporation::mergeMap));

			for (Entry<Long, Map<Integer, Integer>> e : newitems.entrySet()) {
				ObservableMap<Integer, Integer> om = assets.get(e.getKey());
				if (om == null) {
					om = FXCollections.observableHashMap();
					assets.put(e.getKey(), om);
				}
				om.keySet().retainAll(e.getValue().keySet());
				om.putAll(e.getValue());
			}
		} catch (Exception e) {
			logger.warn("while getting assets", e);
		}
		return assets;
	}

	private static Map<Integer, Integer> makeMap(R_get_corporations_corporation_id_assets asset) {
		Map<Integer, Integer> ret = new HashMap<>();
		ret.put(asset.type_id, asset.quantity);
		return ret;
	}

	private static Map<Integer, Integer> mergeMap(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
		for (Entry<Integer, Integer> e : m2.entrySet()) {
			m1.merge(e.getKey(), e.getValue(), (a, b) -> a + b);
		}
		return m1;
	}

	public ObsMapHolder<Long, R_get_corporations_corporation_id_blueprints> getBlueprints() {
		return ObsMapHolderImpl.toMap(con.raw.cache.corporations.blueprints(getId()), bp -> bp.item_id);
	}

	private ObsMapHolder<Long, R_get_corporations_corporation_id_structures> structures = null;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_structures> getStructures() {
		if (structures == null) {
			synchronized (this) {
				if (structures == null) {
					structures = ObsMapHolderImpl.toMap(con.raw.cache.corporations.structures(getId()), str -> str.structure_id);
				}
			}
		}
		return structures;
	}

	ObsObjHolderImpl<Double> wallet = null;

	/** get the total sum of all the wallets */
	public ObsObjHolder<Double> getWallet() {
		if (wallet == null) {
			synchronized (this) {
				if (wallet == null) {
					SimpleObjectProperty<Double> underlying = new SimpleObjectProperty<>();
					wallet = new ObsObjHolderImpl<>(underlying);
					con.raw.cache.corporations.wallets(getId()).followItems(c -> {
						double delta = 0;
						while (c.next()) {
							delta += c.getAddedSubList().stream().mapToDouble(w1 -> w1.balance).sum()
									- c.getRemoved().stream().mapToDouble(w2 -> w2.balance).sum();
						}
						underlying.set(underlying.get() == null ? delta : underlying.get() + delta);
					});
				}
			}
		}
		return wallet;
	}

	public ObsObjHolder<R_get_corporations_corporation_id_divisions> getDivisions() {
		return con.raw.cache.corporations.divisions(getId());
	}

	ObsMapHolder<String, R_get_corporations_corporation_id_wallets_division_transactions> walletTransactions = null;

	/**
	 * get wallet history.<br />
	 * The key is String because a transaction can appear in the corporation and
	 * character wallets, with same id.
	 */
	public ObsMapHolder<String, R_get_corporations_corporation_id_wallets_division_transactions> getWalletTransactions() {
		if (walletTransactions == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (walletTransactions == null) {
					@SuppressWarnings("unchecked")
					ObsMapHolderImpl<String, R_get_corporations_corporation_id_wallets_division_transactions>[] wallets = Stream
					.of(getDivisions().get().wallet)
					.map(division -> con.raw.cache.corporations.wallets_transactions(getId(), division.division, null))
					.map(l -> ObsMapHolderImpl.toMap(l, k -> "" + getId() + k.transaction_id)).collect(Collectors.toList())
					.toArray(new ObsMapHolderImpl[] {});
					walletTransactions = wallets[0].merge(Arrays.copyOfRange(wallets, 1, wallets.length));
				}
			});
		}
		return walletTransactions;
	}

	private ObsMapHolderImpl<Long, R_get_corporations_corporation_id_orders> cacheOrders = null;

	public ObsMapHolderImpl<Long, R_get_corporations_corporation_id_orders> getMarketOrders() {
		if (cacheOrders == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cacheOrders == null) {
					cacheOrders = ObsMapHolderImpl.toMap(con.raw.cache.corporations.orders(getId()), o -> o.order_id);
				}
			});
		}
		return cacheOrders;
	}

	public R_get_corporations_corporation_id getInformations() {
		return ESIStatic.INSTANCE.cache.corporations.get(getId()).get();
	}
}
