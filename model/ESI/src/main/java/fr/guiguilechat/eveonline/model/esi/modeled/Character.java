package fr.guiguilechat.eveonline.model.esi.modeled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import is.ccp.tech.esi.responses.R_get_characters_character_id;
import is.ccp.tech.esi.responses.R_get_characters_character_id_assets;
import is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks;
import is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks_folders;
import is.ccp.tech.esi.responses.R_get_characters_character_id_industry_jobs;
import is.ccp.tech.esi.responses.R_get_characters_character_id_location;
import is.ccp.tech.esi.responses.R_get_characters_character_id_online;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Character {

	protected final ESIAccount con;

	public Character(ESIAccount con) {
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

	private long jobsCacheEnd = 0;

	private ObservableList<R_get_characters_character_id_industry_jobs> jobsCache = FXCollections.observableArrayList();

	/**
	 * fetch the list of industry jobs for this character. If the cache delay is
	 * not expired, uses the cached version.
	 *
	 * @return the internal cache of the jobs for this character. successive calls
	 *         will return the same value.
	 *
	 */
	public ObservableList<R_get_characters_character_id_industry_jobs> getIndustryJobs() {
		synchronized (jobsCache) {
			if (System.currentTimeMillis() >= jobsCacheEnd) {
				ArrayList<R_get_characters_character_id_industry_jobs> ret = new ArrayList<>();
				int maxPage = 1;
				jobsCacheEnd = Long.MAX_VALUE;
				for (int page = 1; page <= maxPage; page++) {
					Map<String, List<String>> headers = new HashMap<>();
					ret.addAll(
							Arrays.asList(con.raw.get_characters_character_id_industry_jobs(con.characterId(), false, headers)));
					if (page == 1) {
						maxPage = ESIConnection.getNbPages(headers);
						jobsCacheEnd = ESIConnection.getCacheExpire(headers);
					}
				}
				jobsCache.clear();
				jobsCache.addAll(ret);
			}
		}
		return jobsCache;
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
				HashMap<Integer, String> folders = new HashMap<>();
				int maxPage = 1;
				for (int page = 1; page <= maxPage; page++) {
					Map<String, List<String>> headers = new HashMap<>();
					for (R_get_characters_character_id_bookmarks_folders f : con.raw
							.get_characters_character_id_bookmarks_folders(con.characterId(), page, headers)) {
						folders.put(f.folder_id, f.name);
					}
					if (page == 1) {
						maxPage = ESIConnection.getNbPages(headers);
						bookmarkCacheEnd = ESIConnection.getCacheExpire(headers);
					}
				}
				cacheBookmarks.keySet().retainAll(folders.values());
				maxPage = 1;
				for (int page = 1; page <= maxPage; page++) {
					Map<String, List<String>> headers = new HashMap<>();
					for (R_get_characters_character_id_bookmarks f : con.raw
							.get_characters_character_id_bookmarks(con.characterId(), page, headers)) {
						String foldName = folders.get(f.folder_id);
						ObservableMap<Integer, R_get_characters_character_id_bookmarks> m = cacheBookmarks.get(foldName);
						if (m == null) {
							m = FXCollections.observableMap(new LinkedHashMap<>());
							cacheBookmarks.put(foldName, m);
						}
						m.put(f.bookmark_id, f);
					}
					if (page == 1) {
						String pages = headers.containsKey("x-pages") ? headers.get("x-pages").get(0) : null;
						maxPage = pages == null ? 1 : Integer.parseInt(pages);
						bookmarkCacheEnd = ESIConnection.getCacheExpire(headers);
					}
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
					cacheOnlineExpire = ESIConnection.getCacheExpire(headerHandler);
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

	protected long cacheLocationExpire = 0;

	protected void fetchLocation() {
		R_get_characters_character_id_location cachedLocation;
		if (cacheLocationExpire <= System.currentTimeMillis()) {
			synchronized (this) {
				if (cacheLocationExpire <= System.currentTimeMillis()) {
					Map<String, List<String>> headerHandler = new HashMap<>();
					cachedLocation = con.raw.get_characters_character_id_location(con.characterId(), headerHandler);
					solarSystem.set(cachedLocation.solar_system_id);
					station.set(cachedLocation.station_id);
					structure.set(cachedLocation.structure_id);
					cacheLocationExpire = ESIConnection.getCacheExpire(headerHandler);
				}
			}
		}
	}

	IntegerProperty solarSystem = new SimpleIntegerProperty();

	public IntegerProperty getSolarSystem() {
		fetchLocation();
		return solarSystem;
	}

	IntegerProperty station = new SimpleIntegerProperty();

	public IntegerProperty getStation() {
		fetchLocation();
		return station;
	}

	LongProperty structure = new SimpleLongProperty();

	public LongProperty getStructure() {
		fetchLocation();
		return structure;
	}

	// system->typeid->number
	private ObservableMap<Integer, ObservableMap<Integer, Integer>> cachedAssets = FXCollections
			.observableMap(new LinkedHashMap<>());

	private long assetsExpire = 0;

	public ObservableMap<Integer, ObservableMap<Integer, Integer>> getAssets() {
		if (assetsExpire < System.currentTimeMillis()) {
			Map<String, List<String>> headerHandler = new HashMap<>();
			R_get_characters_character_id_assets[] res = con.raw.get_characters_character_id_assets(con.characterId(), 1, headerHandler);
			int nbpages = ESIConnection.getNbPages(headerHandler);
			assetsExpire = ESIConnection.getCacheExpire(headerHandler);
			Stream
			.concat(Stream.of(res),
					IntStream.rangeClosed(2, nbpages).parallel()
									.mapToObj(page -> con.raw.get_characters_character_id_assets(con.characterId(), page, null))
									.flatMap(Stream::of))
					.forEachOrdered(this::addAsset);
		}
		return cachedAssets;
	}

	private void addAsset(R_get_characters_character_id_assets asset) {

	}

}
