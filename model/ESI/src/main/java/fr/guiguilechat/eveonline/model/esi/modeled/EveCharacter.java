package fr.guiguilechat.eveonline.model.esi.modeled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_assets;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_blueprints;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_bookmarks;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_online;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.modeled.character.LocationCache;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class EveCharacter {

	protected final ESIAccount con;

	public EveCharacter(ESIAccount con) {
		this.con = con;
	}

	// character informations

	R_get_characters_character_id infos = null;

	protected synchronized R_get_characters_character_id getInfos() {
		if (infos == null) {
			infos = con.raw.get_characters_character_id(con.characterId(), null);
		}
		return infos;
	}

	public String name() {
		return getInfos().name;
	}

	public String description() {
		return getInfos().description;
	}

	public int corporation_id() {
		return getInfos().corporation_id;
	}

	public int alliance_id() {
		return getInfos().alliance_id;
	}

	public String birthday() {
		return getInfos().birthday;
	}

	public String gender() {
		return getInfos().gender;
	}

	public long race_id() {
		return getInfos().race_id;
	}

	public long bloodline_id() {
		return getInfos().bloodline_id;
	}

	public long ancestry_id() {
		return getInfos().ancestry_id;
	}

	public double security_status() {
		return getInfos().security_status;
	}

	public long faction_id() {
		return getInfos().faction_id;
	}

	// industry jobs

	private ObservableMap<Integer, R_get_characters_character_id_industry_jobs> jobsCache = null;

	private CountDownLatch jobLatch = new CountDownLatch(1);

	/**
	 * wait until the industry jobs are fetched
	 */
	public void ensureIndustryJobs() {
		// otherwise latch is null
		getIndustryJobs();
		try {
			jobLatch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	/**
	 * fetch the list of industry jobs for this character. If the cache delay is
	 * not expired, uses the cached version.
	 *
	 * @return the internal cache of the jobs for this character. successive calls
	 *         will return the same value.
	 *
	 */
	public ObservableMap<Integer, R_get_characters_character_id_industry_jobs> getIndustryJobs() {
		synchronized (jobLatch) {
			if (jobsCache == null) {
				jobsCache = FXCollections.observableHashMap();
				con.addFetchCacheArray((p, h) -> con.raw.get_characters_character_id_industry_jobs(con.characterId(), false, h),
						this::handleNewJobs);
			}
		}
		return jobsCache;
	}

	private void handleNewJobs(Stream<R_get_characters_character_id_industry_jobs> newCache) {
		Map<Integer, R_get_characters_character_id_industry_jobs> newitems = newCache
				.collect(Collectors.toMap(job -> job.job_id, job -> job));
		synchronized (jobsCache) {
			jobsCache.keySet().retainAll(newitems.keySet());
			jobsCache.putAll(newitems);
		}
		jobLatch.countDown();
	}

	public static boolean isManufacture(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 1;
	}

	public static boolean isTE(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 3;
	}

	public static boolean isME(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 4;
	}

	public static boolean isCopy(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 5;
	}

	public static boolean isInvention(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 8;
	}

	private long bookmarkCacheEnd = 0;

	private ObservableMap<String, ObservableMap<Integer, R_get_characters_character_id_bookmarks>> cacheBookmarks = FXCollections
			.observableMap(new LinkedHashMap<>());

	/**
	 *
	 * @return the cached list of observable bookmarks, by folder->id->bookmark.
	 */
	public ObservableMap<String, ObservableMap<Integer, R_get_characters_character_id_bookmarks>> getBookmarks() {
		synchronized (cacheBookmarks) {
			if (System.currentTimeMillis() >= bookmarkCacheEnd) {
				// first we get all the folders.
				Map<Integer, String> folders = ESIConnection
						.loadPages((p, h) -> con.raw.get_characters_character_id_bookmarks_folders(con.characterId(), p, h),
								l -> bookmarkCacheEnd = l)
						.collect(Collectors.toMap(f -> f.folder_id, f -> f.name));
				cacheBookmarks.keySet().retainAll(folders.values());
				List<R_get_characters_character_id_bookmarks> bms = ESIConnection
						.loadPages((p, h) -> con.raw.get_characters_character_id_bookmarks(con.characterId(), p, h),
								l -> bookmarkCacheEnd = Math.min(l, bookmarkCacheEnd))
						.collect(Collectors.toList());
				for (R_get_characters_character_id_bookmarks f : bms) {
					String foldName = folders.get(f.folder_id);
					ObservableMap<Integer, R_get_characters_character_id_bookmarks> m = cacheBookmarks.get(foldName);
					if (m == null) {
						m = FXCollections.observableMap(new LinkedHashMap<>());
						cacheBookmarks.put(foldName, m);
					}
					m.put(f.bookmark_id, f);
				}
			}
		}
		return cacheBookmarks;
	}

	protected R_get_characters_character_id_online cachedOnline = null;

	protected long cacheOnlineExpire = 0;

	protected R_get_characters_character_id_online getOnline() {
		if (cacheOnlineExpire <= System.currentTimeMillis()) {
			synchronized (this) {
				if (cacheOnlineExpire <= System.currentTimeMillis()) {
					Map<String, List<String>> headerHandler = new HashMap<>();
					cachedOnline = con.raw.get_characters_character_id_online(con.characterId(), headerHandler);
					cacheOnlineExpire = System.currentTimeMillis() + ESIConnection.getCacheExpire(headerHandler);
				}
			}
		}
		return cachedOnline;
	}

	public boolean isOnline() {
		return getOnline().online;
	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	static {
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	public Date lastLogin() {
		try {
			synchronized (sdf) {
				return sdf.parse(getOnline().last_login);
			}
		} catch (ParseException ex) {
			throw new UnsupportedOperationException("catch this", ex);
		}
	}

	public Date lastLogout() {
		try {
			synchronized (sdf) {
				return sdf.parse(getOnline().last_logout);
			}
		} catch (ParseException ex) {
			throw new UnsupportedOperationException("catch this", ex);
		}
	}

	private LocationCache location = null;

	public LocationCache getLocation() {
		if (location == null) {
			synchronized (this) {
				if (location == null) {
					location = new LocationCache(con);
					try {
						location.waitData();
					} catch (InterruptedException e) {
						throw new UnsupportedOperationException("catch this", e);
					}
				}
			}
		}
		return location;
	}

	// system->typeid->number
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
				R_get_characters_character_id_assets[] itemsArr = ESIConnection
						.loadPages((p, h) -> con.raw.get_characters_character_id_assets(con.characterId(), p, h),
								l -> assetsExpire = l)
						.filter(asset -> !"AutoFit".equals(asset.location_flag))
						.toArray(R_get_characters_character_id_assets[]::new);
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
						.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), EveCharacter::makeMap, EveCharacter::mergeMap));
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

	private static Map<Integer, Integer> makeMap(R_get_characters_character_id_assets asset) {
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

	private Map<Integer, Integer> skills = null;

	public synchronized Map<Integer, Integer> getSkills() {
		if (skills == null) {
			skills = Stream.of(con.raw.get_characters_character_id_skills(con.characterId(), null).skills)
					.collect(Collectors.toMap(s -> s.skill_id, s -> s.active_skill_level));
		}
		return skills;
	}

	public ObservableNumberValue availableResearchSlots() {
		ObservableMap<Integer, R_get_characters_character_id_industry_jobs> jobs = getIndustryJobs();
		LongBinding charJobsVar = Bindings.createLongBinding(() -> {
			ensureIndustryJobs();
			synchronized (jobs) {
				return jobs.values().stream().filter(j -> isCopy(j) || isInvention(j) || isME(j) || isTE(j)).count();
			}
		}, jobs);
		ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> corpJobs = con.corporation
				.getIndustryJobs();
		LongBinding corpJobsVar = Bindings.createLongBinding(() -> {
			con.corporation.ensureIndustryJobs();
			synchronized (corpJobs) {
				return corpJobs.values().stream().filter(j -> j.installer_id == con.characterId())
						.filter(
								j -> Corporation.isCopy(j) || Corporation.isInvetion(j) || Corporation.isME(j) || Corporation.isTE(j))
						.count();
			}
		}, corpJobs);
		return new SimpleIntegerProperty(1 + getSkills().getOrDefault(3406, 0) + getSkills().getOrDefault(24624, 0))
				.subtract(charJobsVar).subtract(corpJobsVar);
	}

	public ObservableNumberValue availableManufSlots() {
		ObservableMap<Integer, R_get_characters_character_id_industry_jobs> charjobs = getIndustryJobs();
		LongBinding charJobsVar = Bindings.createLongBinding(() -> {
			ensureIndustryJobs();
			synchronized (charjobs) {
				return charjobs.values().stream().filter(j -> isManufacture(j)).count();
			}
		}, charjobs);
		ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> corpJobs = con.corporation
				.getIndustryJobs();
		LongBinding corpJobsVar = Bindings.createLongBinding(() -> {
			con.corporation.ensureIndustryJobs();
			synchronized (corpJobs) {
				return corpJobs.values().stream()
						.filter(j -> j.installer_id == con.characterId()).filter(j -> Corporation.isManufacture(j)).count();
			}
		}, corpJobs);
		return new SimpleIntegerProperty(
				1 + getSkills().getOrDefault(3387, 0) + getSkills().getOrDefault(24625, 0))
				.subtract(charJobsVar).subtract(corpJobsVar);
	}

	private ObservableList<R_get_characters_character_id_blueprints> cachedBlueprints = FXCollections
			.observableArrayList();

	private long cachedBlueprintsExpire = 0;

	public ObservableList<R_get_characters_character_id_blueprints> getBlueprints() {
		if (cachedBlueprintsExpire <= System.currentTimeMillis()) {
			synchronized (cachedBlueprints) {
				if (cachedBlueprintsExpire <= System.currentTimeMillis()) {
					Stream<R_get_characters_character_id_blueprints> bps = ESIConnection.loadPages(
							(p, h) -> con.raw.get_characters_character_id_blueprints(con.characterId(), p, h),
							l -> cachedBlueprintsExpire = l);
					cachedBlueprints.setAll(bps.collect(Collectors.toList()));
				}
			}
		}
		return cachedBlueprints;
	}

}
