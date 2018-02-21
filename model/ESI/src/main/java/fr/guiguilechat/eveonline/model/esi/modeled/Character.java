package fr.guiguilechat.eveonline.model.esi.modeled;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.eveonline.model.esi.ESIConnection;
import is.ccp.tech.esi.responses.R_get_characters_character_id;
import is.ccp.tech.esi.responses.R_get_characters_character_id_industry_jobs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Character {

	protected final ESIConnection con;

	public Character(ESIConnection con) {
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

	ObservableList<R_get_characters_character_id_industry_jobs> jobsCache = FXCollections.observableArrayList();

	public ObservableList<R_get_characters_character_id_industry_jobs> jobs() {
		if (System.currentTimeMillis() >= jobsCacheEnd) {
			ArrayList<R_get_characters_character_id_industry_jobs> ret = new ArrayList<>();
			int maxPage = 1;
			jobsCacheEnd = Long.MAX_VALUE;
			for (int page = 1; page <= maxPage; page++) {
				Map<String, List<String>> headers = new HashMap<>();
				ret.addAll(Arrays.asList(con.raw.get_characters_character_id_industry_jobs(con.characterId(), false, headers)));
				if (page == 1) {
					String pages = headers.containsKey("x-pages") ? headers.get("x-pages").get(0) : null;
					maxPage = pages == null ? 1 : Integer.parseInt(pages);
				}
				jobsCacheEnd = Math.min(jobsCacheEnd,
						System.currentTimeMillis()
						+ 1000 * ZonedDateTime.parse(headers.get("Expires").get(0), ESIConnection.formatter).toEpochSecond()
						- 1000 * ZonedDateTime.parse(headers.get("Date").get(0), ESIConnection.formatter).toEpochSecond());
			}
			jobsCache.clear();
			jobsCache.addAll(ret);
		}
		return jobsCache;
	}

}
