package fr.guiguilechat.eveonline.model.esi.modeled;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_planets;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_planets_planet_id;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class PI {

	protected final ESIAccount acc;

	public PI(ESIAccount raw) {
		acc = raw;
	}

	ObservableMap<Integer, ColonyInfo> cachedPlanets = FXCollections.observableMap(new LinkedHashMap<>());
	long planetCacheExpiry = 0;

	/**
	 * colony info<br />
	 * extends the returned colony info with the basic colony data
	 */
	public static class ColonyInfo extends R_get_characters_character_id_planets_planet_id {

		public ColonyInfo(R_get_characters_character_id_planets_planet_id base) {
			for (Field f : base.getClass().getFields()) {
				try {
					f.set(this, f.get(base));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new UnsupportedOperationException("catch this", e);
				}
			}
		}

		protected void addInfo(R_get_characters_character_id_planets info) {
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

	public ObservableMap<Integer, ColonyInfo> getPlanets() {
		synchronized (cachedPlanets) {
			if (planetCacheExpiry <= System.currentTimeMillis()) {
				Map<String, List<String>> headerHandler = new HashMap<>();
				R_get_characters_character_id_planets[] planetIds = acc.raw
						.get_characters_character_id_planets(acc.characterId(), headerHandler);
				Set<Integer> pids = planetIds == null ? Collections.emptySet()
						: Stream.of(planetIds).parallel().map(pli -> pli.planet_id).collect(Collectors.toSet());
				cachedPlanets.keySet().retainAll(pids);
				planetCacheExpiry = ESIConnection.getCacheExpire(headerHandler);
				if (planetIds != null) {
					Stream.of(planetIds).parallel().map(pli -> {
						R_get_characters_character_id_planets_planet_id planet = acc.raw
								.get_characters_character_id_planets_planet_id(acc.characterId(), pli.planet_id, null);
						if (planet != null) {
							ColonyInfo ret = new ColonyInfo(planet);
							ret.addInfo(pli);
							return ret;
						} else {
							return null;
						}
					}).filter(o -> o != null).forEachOrdered(ci -> cachedPlanets.put(ci.planet_id, ci));
				}
			}
		}
		return cachedPlanets;
	}

}
