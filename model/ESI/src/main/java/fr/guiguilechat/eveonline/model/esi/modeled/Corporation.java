package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_industry_jobs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Corporation {

	protected final ESIAccount con;

	public Corporation(ESIAccount con) {
		this.con = con;
	}

	private ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> jobsCache = FXCollections
			.observableHashMap();
	private long jobsCacheExpire = 0;

	public ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> jobs() {
		synchronized (jobsCache) {
			if (System.currentTimeMillis() >= jobsCacheExpire) {
				Map<String, List<String>> headers = new HashMap<>();
				R_get_corporations_corporation_id_industry_jobs[] firstPage = con.raw
						.get_corporations_corporation_id_industry_jobs(con.character.corporation_id(), false, 1, headers);
				int nbpages = ESIConnection.getNbPages(headers);
				jobsCacheExpire = ESIConnection.getCacheExpire(headers);
				Map<Integer, R_get_corporations_corporation_id_industry_jobs> newitems = Stream
						.concat(Stream.of(firstPage),
								IntStream.rangeClosed(2, nbpages).parallel()
								.mapToObj(page -> con.raw.get_corporations_corporation_id_industry_jobs(
										con.character.corporation_id(), false, page, null))
								.flatMap(Stream::of))
						.collect(Collectors.toMap(job -> job.job_id, job -> job));
				jobsCache.keySet().retainAll(newitems.keySet());
				jobsCache.putAll(newitems);
			}
		}
		return jobsCache;
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
						l -> bookmarkCacheExpire = l).collect(Collectors.toMap(f -> f.folder_id, f -> f.name));
				cacheBookmarks.keySet().retainAll(folders.values());
				List<R_get_corporations_corporation_id_bookmarks> bms = ESIConnection.loadPages(
						(p, h) -> con.raw.get_corporations_corporation_id_bookmarks(con.character.corporation_id(), p, h),
						l -> bookmarkCacheExpire = Math.min(l, bookmarkCacheExpire)).collect(Collectors.toList());
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
	}

}
