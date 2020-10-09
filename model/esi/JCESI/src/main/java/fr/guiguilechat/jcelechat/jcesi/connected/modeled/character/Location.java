package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_location;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsIntHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsLongHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

/**
 * fetch the location of the player once the cache is expired.
 *
 */
public class Location {

	private final ESIAccount con;

	public Location(ESIAccount acc) {
		con = acc;
	}

	public ObsObjHolder<R_get_characters_character_id_location> get() {
		return con.raw.cache.characters.location(con.characterId());
	}

	protected ObsIntHolder solarSystem = null;

	public ObsIntHolder getSolarSystemID() {
		if (solarSystem == null) {
			ObsObjHolder<R_get_characters_character_id_location> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (solarSystem == null) {
					solarSystem = fetch.mapInt(info -> info.solar_system_id);
				}
			});
		}
		return solarSystem;
	}

	protected ObsIntHolder stationID = null;

	public ObsIntHolder getStationID() {
		if (stationID == null) {
			ObsObjHolder<R_get_characters_character_id_location> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (stationID == null) {
					stationID = fetch.mapInt(info -> info.station_id);
				}
			});
		}
		return stationID;
	}

	protected ObsLongHolder structureID = null;

	public ObsLongHolder getStructureID() {
		if (structureID == null) {
			ObsObjHolder<R_get_characters_character_id_location> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (structureID == null) {
					structureID = fetch.mapLong(info -> info.structure_id);
				}
			});
		}
		return structureID;
	}

}
