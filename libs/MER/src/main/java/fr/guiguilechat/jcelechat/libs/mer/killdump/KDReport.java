package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDParser.KDEntry;
import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Count;
import fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis.Value;

/**
 * generates a report from the parsed killdumps
 */
public class KDReport {

	/**
	 * parses the killdumps, filter them, aggregates per month, then apply
	 * min/max/etc value per predicate over each aggregate
	 *
	 * @param filter
	 *          eliminates the kills before making month aggregation
	 * @param predicates
	 *          the predicates for which to create sub group per month aggregate
	 * @return the csv of the report
	 */
	@SuppressWarnings("unchecked")
	public static String generate(Predicate<KDEntry> filter,
			List<ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>>> aaxis, Predicate<KDEntry>... predicates) {
		StringWriter sw = new StringWriter();
		@SuppressWarnings("resource")
		CSVWriter writer = new CSVWriter(sw, '\t', ICSVWriter.DEFAULT_QUOTE_CHARACTER, ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
				ICSVWriter.DEFAULT_LINE_END);

		List<String> header = new ArrayList<>();
		header.add("date");
		header.add("total");
		if(predicates!=null) {
			for(Predicate<KDEntry> pr : predicates) {
				for (ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> axis : aaxis) {
					header.add(pr.toString() + "_" + axis.toString());
				}
			}
		}
		writer.writeNext(header.toArray(String[]::new), false);

		KDParser parser = new KDParser();
		parser.byMonth(filter).entrySet().stream().sorted(Comparator.comparing(e->e.getKey())).forEach(e->{
			List<String> row = new ArrayList<>();
			row.add(e.getKey());
			List<KDEntry> values = e.getValue();
			row.add("" + values.size());
			if (predicates != null) {
				for (Predicate<KDEntry> pr : predicates) {
					List<KDEntry> filtered = values.stream().filter(pr).collect(Collectors.toList());
					for (ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> axis : aaxis) {
						double val = axis.applyAsDouble(filtered, values);
						if (val == (int) val) {
							row.add("" + (int) val);
						} else {
							row.add(String.format("%,.2f", val));
						}
					}
				}
			}
			writer.writeNext(row.toArray(String[]::new), false);
		});
		return sw.toString();
	}

	@SuppressWarnings("unchecked")
	public static String generate(Predicate<KDEntry> filter, Predicate<KDEntry>... predicates) {
		return generate(filter, List.of(Count.AGG, Count.PCT, Value.SUM, Value.PCT), predicates);
	}

}
