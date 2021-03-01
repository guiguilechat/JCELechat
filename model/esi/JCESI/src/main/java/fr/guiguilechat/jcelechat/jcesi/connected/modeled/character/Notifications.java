package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_notifications;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_notifications_type;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import lombok.Getter;

public class Notifications {

	private final ESIAccount con;

	public Notifications(ESIAccount acc) {
		con = acc;
	}

	@Getter(lazy = true)
	private final ObsListHolder<R_get_characters_character_id_notifications> raw = con.connection().cache().characters
	.notifications(con.characterId());

	@Getter(lazy = true)
	private final ObsMapHolder<Long, R_get_characters_character_id_notifications> byId = getRaw()
	.toMap(notif -> notif.notification_id);

	@Getter(lazy = true)
	private final ObsMapHolder<get_characters_character_id_notifications_type, List<R_get_characters_character_id_notifications>> byType = getRaw()
	.grouping(notif -> notif.type);

}
