package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets_planet_id;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class PI {

	protected final ESIAccount acc;

	public PI(ESIAccount raw) {
		acc = raw;
	}

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

	private ObsMapHolder<Integer, ColonyInfo> planets = null;

	public ObsMapHolder<Integer, ColonyInfo> getPlanets() {
		if (planets == null) {
			synchronized (this) {
				if (planets == null) {
					ObservableMap<Integer, ColonyInfo> mcol = FXCollections.observableHashMap();
					planets = new ObsMapHolderImpl<>(mcol);
					acc.raw.cache.characters.planets(acc.characterId()).follow(c -> {
						HashSet<Integer> removed = new HashSet<>();
						List<R_get_characters_character_id_planets> added = new ArrayList<>();
						while (c.next()) {
							if (c.wasRemoved()) {
								for (R_get_characters_character_id_planets e1 : c.getRemoved()) {
									removed.add(e1.planet_id);
								}
							}
							if (c.wasAdded()) {
								for (R_get_characters_character_id_planets e2 : c.getAddedSubList()) {
									if (!removed.remove(e2.planet_id)) {
										added.add(e2);
									}
								}
							}
						}
						mcol.keySet().removeAll(removed);
						for (R_get_characters_character_id_planets a : added) {
							acc.raw.cache.characters.planets(acc.characterId(), a.planet_id)
							.follow((observable, oldValue, newValue) -> {
								if (newValue == null) {
									synchronized (mcol) {
										mcol.remove(a.planet_id);
									}
								} else {
									ColonyInfo added1 = new ColonyInfo(newValue);
									added1.addInfo(a);
									synchronized (mcol) {
										mcol.put(a.planet_id, added1);
									}
								}
							});
						}
					});
					acc.raw.cache.characters.planets(acc.characterId()).addReceivedListener(l -> planets.dataReceived());
				}
			}
		}
		return planets;
	}

}
