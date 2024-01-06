package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_location;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.numbers.IntHolder;
import fr.lelouet.tools.holders.interfaces.numbers.LongHolder;
import lombok.Getter;

/**
 * fetch the location of the player once the cache is expired.
 *
 */
public class Location {

	private final ESIAccount con;

	public Location(ESIAccount acc) {
		con = acc;
	}

	public ObjHolder<R_get_characters_character_id_location> getRaw() {
		return con.connection().cache().characters.location(con.characterId());
	}

	@Getter(lazy = true)
	private final IntHolder solarSystemID = getRaw().mapInt(info -> info == null ? 0 : info.solar_system_id);

	@Getter(lazy = true)
	private final IntHolder stationID = getRaw().mapInt(info -> info == null ? 0 : info.station_id);


	@Getter(lazy = true)
	private final LongHolder structureID = getRaw().mapLong(info -> info == null ? 0 : info.structure_id);


}
