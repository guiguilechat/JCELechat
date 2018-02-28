package fr.guiguilechat.eveonline.model.esi.modeled;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import is.ccp.tech.esi.responses.R_get_characters_character_id_location;

public class Route {

	protected final ESIAccount con;

	public Route(ESIAccount con) {
		this.con = con;
	}

	public void clear() {
		R_get_characters_character_id_location loc = con.raw.get_characters_character_id_location(con.characterId(), null);
		con.raw.post_ui_autopilot_waypoint(true, true, loc.solar_system_id, null);
	}

	public void append(long locationId) {
		con.raw.post_ui_autopilot_waypoint(false, false, locationId, null);
	}

}
