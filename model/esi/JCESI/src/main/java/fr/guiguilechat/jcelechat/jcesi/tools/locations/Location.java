package fr.guiguilechat.jcelechat.jcesi.tools.locations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
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

	/**
	 * return the system thi
	 *
	 * @return
	 */
	public R_get_universe_stations_station_id station() {
		if (ref == null) {
			return null;
		}

		switch (type) {
		case REGION:
		case CONSTEL:
		case SYSTEM:
		case CONQSTATION:
		case OFFICE:
		case STRUCTURE:
			return null;
		case STATION:
			return (R_get_universe_stations_station_id) ref;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	/**
	 *
	 * @return the system of the ref if possible, or null.
	 */
	public R_get_universe_systems_system_id system() {
		if (ref == null) {
			return null;
		}
		switch (type) {
		case REGION:
		case CONSTEL:
			return null;
		case SYSTEM:
			return (R_get_universe_systems_system_id) ref;
		case CONQSTATION:
		case OFFICE:
		case STATION:
			return ESIStatic.INSTANCE.cache().universe.systems(((R_get_universe_stations_station_id) ref).system_id).get();
		case STRUCTURE:
			return ESIStatic.INSTANCE.cache().universe.systems(((R_get_universe_structures_structure_id) ref).solar_system_id)
					.get();
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	/**
	 *
	 * @return the constellation of the ref if possible, or null.
	 */
	public R_get_universe_constellations_constellation_id constel() {
		if (ref == null) {
			return null;
		}
		switch (type) {
		case REGION:
			return null;
		case CONSTEL:
			return (R_get_universe_constellations_constellation_id) ref;
		case SYSTEM:
		case CONQSTATION:
		case OFFICE:
		case STATION:
		case STRUCTURE:
			return ESIStatic.INSTANCE.cache().universe.constellations(system().constellation_id).get();
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	/**
	 * @return the region of the ref if possible, or null
	 */
	public R_get_universe_regions_region_id region() {
		if (ref == null) {
			return null;
		}
		switch (type) {
		case REGION:
			return (R_get_universe_regions_region_id) ref;
		case CONSTEL:
		case SYSTEM:
		case CONQSTATION:
		case OFFICE:
		case STATION:
		case STRUCTURE:
			return ESIStatic.INSTANCE.cache().universe.regions(constel().region_id).get();
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	@Override
	public int hashCode() {
		return (int) id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj.getClass() == this.getClass() && ((Location) obj).id == id;
	}

	@Override
	public String toString() {
		return "" + type + ":" + name + "(" + id + ")";
	}

	/**
	 * @see https://docs.esi.evetech.net/docs/asset_location_id
	 */
	public static Location resolve(ESIAccount account, long locationid) {
		if (locationid < Integer.MAX_VALUE) {
			R_get_universe_stations_station_id station;
			int prefix = (int) locationid / 1000000;
			switch (prefix) {
			case 10:// region
				R_get_universe_regions_region_id region = ESIStatic.INSTANCE.cache().universe.regions((int) locationid).get();
				return new Location(region, locationid, region.name, LOCTYPE.REGION);
			case 20:// constellation
				R_get_universe_constellations_constellation_id constel = ESIStatic.INSTANCE.cache().universe
				.constellations((int) locationid).get();
				return new Location(constel, locationid, constel.name, LOCTYPE.CONSTEL);
			case 30:
			case 31:
			case 32:// system
				R_get_universe_systems_system_id system = ESIStatic.INSTANCE.cache().universe.systems((int) locationid).get();
				return new Location(system, locationid, system.name, LOCTYPE.SYSTEM);
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:// station
				station = ESIStatic.INSTANCE.cache().universe.stations((int) locationid).get();
				return new Location(station, locationid, station.name, LOCTYPE.STATION);
			case 66:// office id
				station = ESIStatic.INSTANCE.cache().universe.stations((int) locationid - 6000001).get();
				return new Location(station, locationid, station.name, LOCTYPE.OFFICE);
			case 67:// conquerable office
				station = ESIStatic.INSTANCE.cache().universe.stations((int) locationid - 6000000).get();
				return new Location(station, locationid, station.name, LOCTYPE.CONQSTATION);
			default:
				logger.warn("locationid not handled " + locationid + " prefix " + prefix);
				return new Location(null, locationid, "unknown" + locationid, LOCTYPE.STRUCTURE);
			}
		} else {
			// TODO check into esiaccess if the structure id is in the public
			// structures.
			if (ESIAccess.INSTANCE.universe.isPublicStructure(locationid)) {
				// can't do anything. stil need an account.
			}
			if (account != null) {
				Requested<R_get_universe_structures_structure_id> req = account.connection().get_universe_structures(locationid,
						null);
				if (req.isOk()) {
					R_get_universe_structures_structure_id struct = req.getOK();
					return new Location(struct, locationid, struct.name, LOCTYPE.STRUCTURE);
				}
			}
			return new Location(null, locationid, "unknown" + locationid, LOCTYPE.STRUCTURE);
		}
	}

}
