package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_notifications;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_notifications_type;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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

	@Getter
	@RequiredArgsConstructor
	@ToString(exclude = { "notification", "charId", "targetId" })
	public static class NPCStandingChange {
		private final int targetId;
		private final int charId;
		private final float diff;
		private final float value1;
		private final float value2;
		private final float result;

		private final R_get_characters_character_id_notifications notification;

		@Getter(lazy = true)
		private final String targetName = makeTargetName();

		protected String makeTargetName() {
			// https://docs.esi.evetech.net/docs/id_ranges.html

			if (targetId <= 1000000) {
				// NPC faction
				return ESIAccess.INSTANCE.universe.getFactionsByID().get().get(targetId).name;
			}
			if (targetId <= 2000000) {
				// NPC corporation

			}
			return "" + targetId;
		}

		public static List<NPCStandingChange> parse(String textdata, R_get_characters_character_id_notifications source) {
			List<NPCStandingChange> ret = new ArrayList<>();
			for (String change : textdata.split("\n- -")) {
				int lineNb = 0;

				int targetId = 0;
				int charId = 0;
				float diff = 0;
				float value1 = 0;
				float value2 = 0;
				float result = 0;

				for (String line : change.split("\n")) {
					if (line.startsWith("  ") || line.startsWith("- ")) {
						line = line.substring(4);
					} else {
						line = line.substring(1);
					}
					switch(lineNb) {
					case 0:
						targetId = Integer.parseInt(line);
						break;
					case 1:
						charId = Integer.parseInt(line);
						break;
					case 2:
						diff = Float.parseFloat(line);
						break;
					case 3:
						value1 = Float.parseFloat(line);
						break;
					case 4:
						value2 = Float.parseFloat(line);
						break;
					case 5:
						result = Float.parseFloat(line);
						break;
					default:
						throw new UnsupportedOperationException("not handled " + lineNb);
					}
					lineNb++;
				}
				ret.add(new NPCStandingChange(targetId, charId, diff, value1, value2, result, source));
			}
			return ret;
		}

	}

	@Getter(lazy = true)
	private final ObsListHolder<NPCStandingChange> standingChanges = makeStandingChanges();

	protected ObsListHolder<NPCStandingChange> makeStandingChanges() {
		ObsListHolder<R_get_characters_character_id_notifications> filtered = getRaw()
				.filter(n -> n.type == get_characters_character_id_notifications_type.NPCStandingsLost);
		return filtered.mapList(l -> l.stream().flatMap(not->NPCStandingChange.parse(not.text, not).stream())
				.collect(Collectors.toList()));
	}

}
