package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_industry_jobs;

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
}
