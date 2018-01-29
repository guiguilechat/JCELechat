package fr.guiguilechat.eveonline.model.esi.connect.modeled;

import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_industry_jobs;

public class Corporation {

	protected final ESIConnection con;

	public Corporation(ESIConnection con) {
		this.con = con;
	}

	public R_get_corporations_corporation_id_industry_jobs[] jobs() {
		return con.raw.get_corporations_corporation_id_industry_jobs(con.character.corporation_id(), false);
	}
}
