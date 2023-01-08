package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.lelouet.tools.application.xdg.XDGApp;

/** parses the killdumps in the MER files */
public class KDParser {

	private static final XDGApp APP = new XDGApp("mer.ccp.is");

	public static class KDEntry {

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

		public LocalDateTime getDate() {
			return LocalDateTime.parse(killDateTime(), DATEREADER);
		}

		// victim corporation id

		@CsvBindByName
		public Long victimCorporationID;
		@CsvBindByName
		public Long victim_corporation_id;

		public Long victimCorporationID() {
			if (victimCorporationID != null) {
				return victimCorporationID;
			}
			return victim_corporation_id;
		}

		// killer corporation id

		@CsvBindByName
		public Long finalCorporationID;
		@CsvBindByName
		public Long killer_corporation_id;

		public Long killerCorporationID() {
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

	public List<KDEntry> parse(File MERRooot) {
		return parseDump(findKD(MERRooot));
	}

	protected static File findKD(File MERRoot) {
		for (File child : MERRoot.listFiles()) {
			if (child.isDirectory()) {
				File ret = findKD(child);
				if (ret != null) {
					return ret;
				}
			} else {
				if (child.getName().toLowerCase().endsWith("dump.csv")) {
					return child;
				}
			}
		}
		return null;
	}

	protected static final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

	protected static List<KDEntry> parseDump(File dumpFile) {
		List<KDEntry> ret = new ArrayList<>();
		if (dumpFile != null) {
			try {
				CsvToBean<KDEntry> csvToBean = new CsvToBeanBuilder<KDEntry>(
						new CSVReaderBuilder(new FileReader(dumpFile)).withCSVParser(parser).build())
						.withType(KDEntry.class).withIgnoreLeadingWhiteSpace(true).build();
				ret = csvToBean.parse();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		// System.out.println("file=" + dumpFile + " kills=" + ret.size());
		return ret;
	}

	public Stream<KDEntry> stream() {
		return Stream.of(APP.cacheFile().listFiles()).parallel().map(this::parse).flatMap(List::stream);
	}

	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
	public Map<String, List<KDEntry>> byMonth(Predicate<KDEntry> filter){
		Map<String, List<KDEntry>> grouped = (filter == null ? stream() : stream().filter(filter))
				.collect(Collectors.groupingBy(e -> e.getDate().format(dtf)));
		return grouped;
	}


}
