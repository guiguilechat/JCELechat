package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.lang.reflect.Field;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets_planet_id;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class PI {

	@Getter
	@Accessors(fluent = true)
	protected final ESIAccount account;

	/**
	 * colony info<br />
	 * extends the returned colony info with the basic colony data
	 */
	public static class ColonyInfo extends R_get_characters_character_id_planets_planet_id {

		public ColonyInfo(R_get_characters_character_id_planets info,
				R_get_characters_character_id_planets_planet_id base) {
			addInfo(base);
			addInfo(info);
		}

		public void addInfo(R_get_characters_character_id_planets_planet_id data) {
			for (Field f : data.getClass().getFields()) {
				try {
					f.set(this, f.get(data));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new UnsupportedOperationException("catch this", e);
				}
			}
		}

		public void addInfo(R_get_characters_character_id_planets info) {
			for (Field f : info.getClass().getFields()) {
				try {
					getClass().getField(f.getName()).set(this, f.get(info));
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
					throw new UnsupportedOperationException("catch this", e);
				}
			}
		}

		public int solar_system_id;
		public int planet_id;
		public int owner_id;
		public int upgrade_level;
		public int num_pins;
		public String last_update;
		public String planet_type;

	}

	@Getter(lazy = true)
	private final MapHolder<Object, R_get_characters_character_id_planets> planetsList = account.connection()
	.cache().characters.planets(account.characterId()).toMap(p -> p.planet_id);

	@Getter(lazy = true)
	private final MapHolder<Integer, ColonyInfo> colonies = getPlanetsList()
	.values().unpackItems(info -> account().connection().cache().characters
			.planets(account().characterId(), info.planet_id).map(data -> new ColonyInfo(info, data)))
	.toMap(ci -> ci.planet_id);

}
