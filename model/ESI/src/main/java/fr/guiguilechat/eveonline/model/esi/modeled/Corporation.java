package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.compiled.Swagger;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_bookmarks;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Corporation {

	protected final ESIAccount con;

	public Corporation(ESIAccount con) {
		this.con = con;
	}

	// industry jobs

	private ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> jobsCache = null;

	private CountDownLatch jobLatch = new CountDownLatch(1);

	// /**
	// * wait until the industry jobs are fetched
	// */
	// public void ensureIndustryJobs() {
	// // otherwise latch is null
	// getIndustryJobs();
	// try {
	// jobLatch.await();
	// } catch (InterruptedException e) {
	// throw new UnsupportedOperationException("catch this", e);
	// }
	// }

	public ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> getIndustryJobs() {
		synchronized (jobLatch) {
			if (jobsCache == null) {
				jobsCache = FXCollections.observableHashMap();
				con.addFetchCacheArray(con.characterName() + ".corporationjobs", (p, h) -> con.raw
						.get_corporations_corporation_id_industry_jobs(con.character.corporation_id(), false, p, h),
						this::handleNewJobs, Swagger.GET_CORPORATIONS_CORPORATION_ID_INDUSTRY_JOBS_ROLES);
			}
		}
		return jobsCache;
	}

	private void handleNewJobs(List<R_get_corporations_corporation_id_industry_jobs> newCache) {
		Map<Integer, R_get_corporations_corporation_id_industry_jobs> newitems = newCache == null ? Collections.emptyMap()
				: newCache.stream()
				.collect(Collectors.toMap(job -> job.job_id, job -> job));
		synchronized (jobsCache) {
			jobsCache.keySet().retainAll(newitems.keySet());
			jobsCache.putAll(newitems);
		}
		jobLatch.countDown();
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

	private long bookmarkCacheExpire = 0;

	private ObservableMap<String, ObservableMap<Integer, R_get_corporations_corporation_id_bookmarks>> cacheBookmarks = FXCollections
			.observableMap(new LinkedHashMap<>());

	/**
	 *
	 * @return the cached list of observable bookmarks, by folder->id->bookmark.
	 */
	public ObservableMap<String, ObservableMap<Integer, R_get_corporations_corporation_id_bookmarks>> getBookmarks() {
		synchronized (cacheBookmarks) {
			if (System.currentTimeMillis() >= bookmarkCacheExpire) {
				// first we get all the folders.
				Map<Integer, String> folders = ESIConnection.loadPages(
						(p, h) -> con.raw.get_corporations_corporation_id_bookmarks_folders(con.character.corporation_id(), p, h),
						l -> bookmarkCacheExpire = l).stream().collect(Collectors.toMap(f -> f.folder_id, f -> f.name));
				cacheBookmarks.keySet().retainAll(folders.values());
				List<R_get_corporations_corporation_id_bookmarks> bms = ESIConnection.loadPages(
						(p, h) -> con.raw.get_corporations_corporation_id_bookmarks(con.character.corporation_id(), p, h),
						l -> bookmarkCacheExpire = Math.min(l, bookmarkCacheExpire));
				for (R_get_corporations_corporation_id_bookmarks f : bms) {
					String foldName = folders.get(f.folder_id);
					ObservableMap<Integer, R_get_corporations_corporation_id_bookmarks> m = cacheBookmarks.get(foldName);
					if (m == null) {
						m = FXCollections.observableMap(new LinkedHashMap<>());
						cacheBookmarks.put(foldName, m);
					}
					m.put(f.bookmark_id, f);
				}
			}
		}
		return cacheBookmarks;
	} // system->typeid->number

	private ObservableMap<Long, ObservableMap<Integer, Integer>> cachedAssets = FXCollections
			.observableMap(new LinkedHashMap<>());

	private long assetsExpire = 0;

	/**
	 *
	 * @return the location->typeid->quantity
	 */
	public ObservableMap<Long, ObservableMap<Integer, Integer>> getAssets() {
		synchronized (cachedAssets) {
			if (assetsExpire < System.currentTimeMillis()) {
				R_get_corporations_corporation_id_assets[] itemsArr = ESIConnection
						.loadPages((p, h) -> con.raw.get_corporations_corporation_id_assets(con.character.corporation_id(), p, h),
								l -> assetsExpire = l)
						.stream().filter(asset -> !"AutoFit".equals(asset.location_flag))
						.toArray(R_get_corporations_corporation_id_assets[]::new);
				// we make the map of itemid->locations. if a location is actually an
				// asset, we
				// iterally map it to this asset's location instead
				Map<Long, Long> baseLocationMap = Stream.of(itemsArr)
						.collect(Collectors.toMap(i -> i.item_id, i -> i.location_id));
				Map<Long, Long> idToLocation = baseLocationMap.entrySet().stream()
						.collect(Collectors.toMap(Entry::getKey, e -> {
							Long ret = e.getValue();
							while (baseLocationMap.containsKey(ret)) {
								ret = baseLocationMap.get(ret);
							}
							return ret;
						}));
				Map<Long, Map<Integer, Integer>> newitems = Stream.of(itemsArr)
						.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), Corporation::makeMap, Corporation::mergeMap));

				for (Entry<Long, Map<Integer, Integer>> e : newitems.entrySet()) {
					ObservableMap<Integer, Integer> om = cachedAssets.get(e.getKey());
					if (om == null) {
						om = FXCollections.observableHashMap();
						cachedAssets.put(e.getKey(), om);
					}
					om.keySet().retainAll(e.getValue().keySet());
					om.putAll(e.getValue());
				}
			}
		}
		return cachedAssets;
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

	private ObservableList<R_get_corporations_corporation_id_blueprints> cachedBlueprints = FXCollections
			.observableArrayList();

	private long cachedBlueprintsExpire = 0;

	public ObservableList<R_get_corporations_corporation_id_blueprints> getBlueprints() {
		if (cachedBlueprintsExpire <= System.currentTimeMillis()) {
			synchronized (cachedBlueprints) {
				if (cachedBlueprintsExpire <= System.currentTimeMillis()) {
					List<R_get_corporations_corporation_id_blueprints> bps = ESIConnection.loadPages(
							(p, h) -> con.raw.get_corporations_corporation_id_blueprints(con.character.corporation_id(), p, h),
							l -> cachedBlueprintsExpire = l);
					if (bps != null) {
						cachedBlueprints.setAll(bps);
					}
				}
			}
		}
		return cachedBlueprints;
	}

}
