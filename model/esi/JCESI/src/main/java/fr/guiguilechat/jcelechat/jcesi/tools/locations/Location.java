package fr.guiguilechat.jcelechat.jcesi.tools.locations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;

public class Location {

	private static Logger logger = LoggerFactory.getLogger(Location.class);

	public static enum LOCTYPE {
		REGION, CONSTEL, SYSTEM, STATION, STRUCTURE, OFFICE, CONQSTATION;
	}

	public long id;
	public String name;
	public Object ref;

	public LOCTYPE type;

	public Location(Object ref, long id, String name, LOCTYPE type) {
		this.ref = ref;
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Override
	public int hashCode() {
		return (int) id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj.getClass() == this.getClass() && ((Location) obj).id == id;
	}

	/**
	 * @see https://docs.esi.evetech.net/docs/asset_location_id
	 */
	public static Location resolve(ESIAccount account, long locationid) {
		if (locationid < Integer.MAX_VALUE) {
			R_get_universe_stations_station_id office;
			switch ((int) locationid / 1000000) {
			case 1:// region
				R_get_universe_regions_region_id region = ESIStatic.INSTANCE.cache.universe.regions((int) locationid).get();
				return new Location(region, locationid, region.name, LOCTYPE.REGION);
			case 2:// constellation
				R_get_universe_constellations_constellation_id constel = ESIStatic.INSTANCE.cache.universe
				.constellations((int) locationid).get();
				return new Location(constel, locationid, constel.name, LOCTYPE.CONSTEL);
			case 30:
			case 31:
			case 32:// system
				R_get_universe_systems_system_id system = ESIStatic.INSTANCE.cache.universe.systems((int) locationid).get();
				return new Location(system, locationid, system.name, LOCTYPE.SYSTEM);
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:// station
				R_get_universe_stations_station_id station = ESIStatic.INSTANCE.cache.universe.stations((int) locationid).get();
				return new Location(station, locationid, station.name, LOCTYPE.STATION);
			case 66:// office id
				office = ESIStatic.INSTANCE.cache.universe.stations((int) locationid - 6000001).get();
				return new Location(office, locationid, office.name, LOCTYPE.OFFICE);
			case 67:// conquerable office
				R_get_universe_stations_station_id conqstat = ESIStatic.INSTANCE.cache.universe
				.stations((int) locationid - 6000000).get();
				return new Location(conqstat, locationid, conqstat.name, LOCTYPE.CONQSTATION);
			default:
				logger.warn("locationid not handled " + locationid);
				return new Location(null, locationid, "unknown" + locationid, LOCTYPE.STRUCTURE);
			}
		} else {
			Requested<R_get_universe_structures_structure_id> req = account.raw.get_universe_structures(locationid, null);
			if (req.isOk()) {
				R_get_universe_structures_structure_id struct = req.getOK();
				return new Location(struct, locationid, struct.name, LOCTYPE.STRUCTURE);
			} else {
				return new Location(null, locationid, "unknown" + locationid, LOCTYPE.STRUCTURE);
			}
		}
	}

}
