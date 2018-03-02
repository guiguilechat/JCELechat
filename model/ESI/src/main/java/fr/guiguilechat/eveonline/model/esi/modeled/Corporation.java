package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks_folders;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_industry_jobs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Corporation {

	protected final ESIAccount con;

	public Corporation(ESIAccount con) {
		this.con = con;
	}

	public ArrayList<R_get_corporations_corporation_id_industry_jobs> jobs() {
		ArrayList<R_get_corporations_corporation_id_industry_jobs> ret = new ArrayList<>();
		int maxPage = 1;
		for (int page = 1; page <= maxPage; page++) {
			Map<String, List<String>> headers = new HashMap<>();
			ret.addAll(Arrays.asList(
					con.raw.get_corporations_corporation_id_industry_jobs(con.character.corporation_id(), false, page, headers)));
			if (page == 1) {
				String pages = headers.containsKey("x-pages") ? headers.get("x-pages").get(0) : null;
				maxPage = pages == null ? 1 : Integer.parseInt(pages);
			}
		}
		return ret;
	}

	private long bookmarkCacheEnd = 0;

	private ObservableMap<String, ObservableMap<Integer, R_get_corporations_corporation_id_bookmarks>> cacheBookmarks = FXCollections
			.observableMap(new LinkedHashMap<>());

	/**
	 *
	 * @return the cached list of observable bookmarks, by folder->id->bookmark.
	 */
	public ObservableMap<String, ObservableMap<Integer, R_get_corporations_corporation_id_bookmarks>> getBookmarks() {
		synchronized (cacheBookmarks) {
			if (System.currentTimeMillis() >= bookmarkCacheEnd) {
				// first we get all the folders.
				HashMap<Integer, String> folders = new HashMap<>();
				int maxPage = 1;
				for (int page = 1; page <= maxPage; page++) {
					Map<String, List<String>> headers = new HashMap<>();
					for (R_get_corporations_corporation_id_bookmarks_folders f : con.raw
							.get_corporations_corporation_id_bookmarks_folders(con.character.corporation_id(), page, headers)) {
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
					for (R_get_corporations_corporation_id_bookmarks f : con.raw
							.get_corporations_corporation_id_bookmarks(con.character.corporation_id(), page, headers)) {
						String foldName = folders.get(f.folder_id);
						ObservableMap<Integer, R_get_corporations_corporation_id_bookmarks> m = cacheBookmarks.get(foldName);
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

}
