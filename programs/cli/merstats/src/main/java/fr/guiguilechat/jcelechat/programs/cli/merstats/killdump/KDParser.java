package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

import java.io.File;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import fr.lelouet.tools.application.xdg.XDGApp;

/** parses the killdumps in the MER files */
public class KDParser {

	private static final XDGApp APP = new XDGApp("mer.ccp.is");

	public static final KDParser INSTANCE = new KDParser();

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

	private Collection<KDEntry> loaded = null;

	public Stream<KDEntry> stream() {
		if (loaded == null) {
			synchronized (parser) {
				if (loaded == null) {
					File[] files = APP.cacheFile().listFiles();
					loaded = files == null ? Collections.emptyList()
							: Stream.of(files).parallel().map(this::parse).flatMap(List::stream).collect(Collectors.toList());
				}
			}
		}
		return loaded.stream();
	}

	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");

	public Map<String, List<KDEntry>> byMonth(Predicate<KDEntry> filter){
		Map<String, List<KDEntry>> grouped = (filter == null ? stream() : stream().filter(filter))
				.collect(Collectors.groupingBy(e -> e.getDate().format(dtf)));
		return grouped;
	}

	public Map<Integer, Map<Integer, Map<Integer, List<KDEntry>>>> groupMonthBySysTypeDay(Predicate<KDEntry> filter, int year, int month) {
		File root = new File(APP.cacheFile(), String.format("%04d-%02d", year, month));
		if (!root.isDirectory()) {
			System.err.println("missing MER root " + root);
			return Collections.emptyMap();
		}
		Map<Integer, Map<Integer, Map<Integer, List<KDEntry>>>> ret = new HashMap<>();
		(filter==null?parse(root).stream():parse(root).stream().filter(filter)).forEach(kde->{
			ret
			.computeIfAbsent(kde.solarsystem_id, i->new HashMap<>())
			.computeIfAbsent(kde.destroyedShipTypeID(), i->new HashMap<>())
			.computeIfAbsent(kde.getDate().getDayOfMonth(), i-> new ArrayList<>())
			.add(kde)
			;
		});
		return ret ;
	}


}
