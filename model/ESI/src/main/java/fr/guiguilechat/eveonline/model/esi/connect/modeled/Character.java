package fr.guiguilechat.eveonline.model.esi.connect.modeled;

import fr.guiguilechat.eveonline.model.esi.connect.ESIRawConnection;
import is.ccp.tech.esi.responses.R_get_characters_character_id;

public class Character {

	protected final ESIRawConnection raw;

	public Character(ESIRawConnection raw) {
		this.raw = raw;
	}

	R_get_characters_character_id infos = null;

	public R_get_characters_character_id getInfos() {
		if (infos == null) {
			infos = raw.get_characters_character_id(raw.verify().CharacterID);
		}
		return infos;
	}

}
