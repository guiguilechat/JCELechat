package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_notifications;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;

public class Notifications {

	private final ESIAccount con;

	public Notifications(ESIAccount acc) {
		con = acc;
	}

	public ObsListHolder<R_get_characters_character_id_notifications> get() {
		return con.raw.cache.characters.notifications(con.characterId());
	}

}
