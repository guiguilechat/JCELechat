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
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_assets_8;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_blueprints_8;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.modeled.corporation.Bookmarks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Corporation {

	protected final ESIAccount con;

	public Corporation(ESIAccount con) {
		this.con = con;
		bms = new Bookmarks(con);
	}

	public final Bookmarks bms;

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
		// this is to correct an ESI bug.
		for (String role : Swagger.GET_CORPORATIONS_CORPORATION_ID_INDUSTRY_JOBS_ROLES) {
			if (role.equals("Factory_Manager")) {
				throw new UnsupportedOperationException("remove the hack here");
			}
		}
		synchronized (jobLatch) {
			if (jobsCache == null) {
				jobsCache = FXCollections.observableHashMap();
				con.raw.cache.addFetchCacheArray(
						con.characterName() + ".corporationjobs", (p, h) -> con.raw
						.get_corporations_industry_jobs(con.character.infos.corporationId().get(), false, p, h),
						this::handleNewJobs, "Factory_Manager"
						// remove hack by replacing the hardcoded string with
						// Swagger.GET_CORPORATIONS_CORPORATION_ID_INDUSTRY_JOBS_ROLES
						);
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
				M_get_assets_8[] itemsArr = ESIConnection
						.loadPages(
								(p, h) -> con.raw.get_corporations_assets(con.character.infos.corporationId().get(), p,
										h),
								l -> assetsExpire = l)
						.stream().filter(asset -> !"AutoFit".equals(asset.location_flag))
						.toArray(M_get_assets_8[]::new);
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

	private static Map<Integer, Integer> makeMap(M_get_assets_8 asset) {
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

	private ObservableList<M_get_blueprints_8> cachedBlueprints = FXCollections
			.observableArrayList();

	private long cachedBlueprintsExpire = 0;

	public ObservableList<M_get_blueprints_8> getBlueprints() {
		if (cachedBlueprintsExpire <= System.currentTimeMillis()) {
			synchronized (cachedBlueprints) {
				if (cachedBlueprintsExpire <= System.currentTimeMillis()) {
					List<M_get_blueprints_8> bps = ESIConnection
							.loadPages(
									(p, h) -> con.raw.get_corporations_blueprints(con.character.infos.corporationId().get(), p,
											h),
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
