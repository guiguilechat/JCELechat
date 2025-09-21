package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.opencsv.bean.CsvBindByName;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Etypes;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class KDEntry {

	// killed ship

	@CsvBindByName
	public Integer victim_ship_type_id;
	@CsvBindByName
	public Integer destroyedShipTypeID;

	public Integer destroyedShipTypeID() {
		if (destroyedShipTypeID != null) {
			return destroyedShipTypeID;
		}
		return victim_ship_type_id;
	}

	public EveType getType() {
		EveType ret = TypeIndex.getType(destroyedShipTypeID());
		if (ret == null) {
			// System.err.println("null type for id " + destroyedShipTypeID());
		}
		return ret;
	}

	public Etypes getEType() {
		Etypes ret = Etypes.LOADER.load().get(destroyedShipTypeID());
		if (ret == null) {
			ret = new Etypes();
			ret.name.put("en", "t_" + destroyedShipTypeID());
			ret.groupID = ret.graphicID = ret.factionID = ret.marketGroupID = 0;
		}
		return ret;
	}

	// solar system

	@CsvBindByName
	public Integer solarSystemID;
	@CsvBindByName
	public Integer solarsystem_id;

	public Integer solarSystemId() {
		if (solarSystemID != null) {
			return solarSystemID;
		}
		return solarsystem_id;
	}

	public SolarSystem getSolarSystem() {
		SolarSystem ret = SolarSystem.getSystem(solarSystemId());
		if (ret == null) {
			// System.err.println("no system for id " + solarSystemId());
		}
		return ret;
	}

	// kill date time

	@CsvBindByName
	public String kill_datetime;
	@CsvBindByName
	public String killTime;

	public String killDateTime() {
		if (kill_datetime != null) {
			return kill_datetime;
		}
		return killTime;
	}

	public static final DateTimeFormatter DATEREADER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT);

	public OffsetDateTime getDate() {
		return LocalDateTime.parse(killDateTime(), DATEREADER).atOffset(ZoneOffset.UTC);
	}

	// victim corporation id

	@CsvBindByName
	public Integer victimCorporationID;
	@CsvBindByName
	public Integer victim_corporation_id;

	public Integer victimCorporationID() {
		if (victimCorporationID != null) {
			return victimCorporationID;
		}
		return victim_corporation_id;
	}

	// killer corporation id

	@CsvBindByName
	public Integer finalCorporationID;
	@CsvBindByName
	public Integer killer_corporation_id;

	public Integer killerCorporationID() {
		if (finalCorporationID != null) {
			return finalCorporationID;
		}
		return killer_corporation_id;
	}

	// isk lost

	@CsvBindByName
	public Double isk_lost;
	@CsvBindByName
	public Double iskLost;

	public Double iskLost() {
		return isk_lost != null ? isk_lost : iskLost;
	}

}