package fr.guiguilechat.jcelechat.libs.mer.files;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * same entry for two different structures. Killdump.csv was before 2021-05 ;
 * kill_dump.csv was since 2021-05
 */
public class KillDumpEntry {

	//
	// general : date and place
	//

	// kill date time

	@CsvBindByName
	public String killTime;
	@CsvBindByName
	public String kill_datetime;

	public String killDateTime() {
		return killTime != null ? killTime : kill_datetime;
	}

	static final DateTimeFormatter DATEREADER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT);

	public Instant getKillDate() {
		return LocalDateTime.parse(killDateTime(), DATEREADER).atOffset(ZoneOffset.UTC).toInstant();
	}

	// solar system id

	@CsvBindByName
	public Integer solarSystemID;
	@CsvBindByName
	public Integer solarsystem_id;

	public Integer solarSystemID() {
		return solarSystemID != null ? solarSystemID : solarsystem_id;
	}

	// solar system name

	@CsvBindByName
	public String solarSystemName;
	@CsvBindByName
	public String solarsystem_name;

	public String solarSystemName() {
		return solarSystemName != null ? solarSystemName : solarsystem_name;
	}

	// region id

	@CsvBindByName
	public Integer regionID;
	@CsvBindByName
	public Integer region_id;

	public Integer regionID() {
		return regionID != null ? regionID : region_id;
	}

	// region name

	@CsvBindByName
	public String regionName;
	@CsvBindByName
	public String region_name;

	public String regionName() {
		return regionName != null ? regionName : region_name;
	}

	//
	// ship destroyed
	//

	// killed ship type id

	@CsvBindByName
	public Integer destroyedShipTypeID;
	@CsvBindByName
	public Integer victim_ship_type_id;

	public Integer destroyedShipTypeID() {
		return destroyedShipTypeID != null ? destroyedShipTypeID : victim_ship_type_id;
	}

	// killed ship type name

	@CsvBindByName
	public String destroyedShipType;
	@CsvBindByName
	public String victim_ship_type_name;

	public String destroyedShipType() {
		return destroyedShipType != null ? destroyedShipType : victim_ship_type_name;
	}

	// killed ship group name

	@CsvBindByName
	public String destroyedShipGroup;
	@CsvBindByName
	public String victim_ship_group_name;

	public String destroyedShipGroup() {
		return destroyedShipGroup != null ? destroyedShipGroup : victim_ship_group_name;
	}

	// isk lost

	@CsvBindByName
	public Double iskLost;
	@CsvBindByName
	public Double isk_lost;

	public Double iskLost() {
		return isk_lost != null ? isk_lost : iskLost;
	}

	// isk destroyed (fit value - dropped value)

	@CsvBindByName
	public Double iskDestroyed;
	@CsvBindByName
	public Double isk_destroyed;

	public Double iskDestroyed() {
		return iskDestroyed != null ? iskDestroyed : isk_destroyed;
	}

	// bounty claimed

	@CsvBindByName
	public Double bountyClaimed;
	@CsvBindByName
	public Double bounty_claimed;

	public Double bountyClaimed() {
		return bountyClaimed != null ? bountyClaimed : bounty_claimed;
	}

	//
	// victim
	//

	// victim corporation id

	@CsvBindByName
	public Integer victimCorporationID;
	@CsvBindByName
	public Integer victim_corporation_id;

	public Integer victimCorporationID() {
		return victimCorporationID != null ? victimCorporationID : victim_corporation_id;
	}

	// victim corporation name

	@CsvBindByName
	public String victimCorp;
	@CsvBindByName
	public String victim_corporation_name;

	public String victimCorporation() {
		return victimCorp != null ? victimCorp : victim_corporation_name;
	}

	// victim alliance name

	@CsvBindByName
	public String victimAlliance;
	@CsvBindByName
	public String victim_alliance_name;

	public String victimAlliance() {
		return victimAlliance != null ? victimAlliance : victim_alliance_name;
	}

	//
	// killer / final shot
	//

	// killer corporation id

	@CsvBindByName
	public Integer finalCorporationID;
	@CsvBindByName
	public Integer killer_corporation_id;

	public Integer killerCorporationID() {
		return finalCorporationID != null ? finalCorporationID : killer_corporation_id;

	}

	// killer corporation name

	@CsvBindByName
	public String finalCorp;
	@CsvBindByName
	public String killer_corporation_name;

	public String killerCorporation() {
		return finalCorp != null ? finalCorp : killer_corporation_name;
	}

	// killer alliance name

	@CsvBindByName
	public String finalAlliance;
	@CsvBindByName
	public String killer_alliance_name;

	public String killerAlliance() {
		return finalAlliance != null ? finalAlliance : killer_alliance_name;
	}

	//
	// loader
	//

	private static final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

	public static List<KillDumpEntry> parse(InputStream is) {
		List<KillDumpEntry> ret = new ArrayList<>();
		try {
			synchronized (parser) {
				CsvToBean<KillDumpEntry> csvToBean = new CsvToBeanBuilder<KillDumpEntry>(
						new CSVReaderBuilder(new InputStreamReader(is)).withCSVParser(parser).build())
						.withType(KillDumpEntry.class).withIgnoreLeadingWhiteSpace(true).build();
				ret = csvToBean.parse();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

}