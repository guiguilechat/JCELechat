package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDParser.KDEntry;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;

/**
 * generates a report from the parsed killdumps
 */
public class KDReport {

	public static class GroupFilter implements Predicate<KDEntry> {

		protected static Map<Integer, String> groupToName = makeGroupToName();

		protected static Map<Integer, String> makeGroupToName() {
			Stream<IMetaGroup<?>> imgs = Stream.of(IMetaCategory.INSTANCES).flatMap(imc -> imc.groups().stream());
			return imgs.collect(Collectors.toMap(img -> img.getGroupId(), img -> img.getName()));
		}

		protected final int groupId;

		public GroupFilter(int groupId) {
			this.groupId = groupId;
		}

		@Override
		public boolean test(KDEntry t) {
			return t.getType() != null && t.getType().getGroupId() == groupId;
		}

		@Override
		public String toString() {
			return groupToName.getOrDefault(groupId, "g_" + groupId);
		}

	}

	public static class TypeFilter implements Predicate<KDEntry> {

		protected final int typeId;

		public TypeFilter(int typeId) {
			this.typeId = typeId;
		}

		@Override
		public boolean test(KDEntry t) {
			return t.getType() != null && t.getType().id == typeId;
		}

		@Override
		public String toString() {
			return TypeIndex.getType(typeId).name;
		}

	}

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
	public static String generate(Predicate<KDEntry> filter, Predicate<KDEntry>... predicates) {
		StringWriter sw = new StringWriter();
		CSVWriter writer = new CSVWriter(sw, '\t', ICSVWriter.DEFAULT_QUOTE_CHARACTER, ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
				ICSVWriter.DEFAULT_LINE_END);

		List<String> header = new ArrayList<>();
		header.add("date");
		header.add("total");
		if(predicates!=null) {
			for(Predicate<KDEntry> pr : predicates) {
				header.add(pr.toString()+"_qty");
				header.add(pr.toString() + "_totPrice");
				header.add(pr.toString() + "_avgPrice");
				header.add(pr.toString() + "_minPrice");
				header.add(pr.toString() + "_maxPrice");
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
					row.add("" + filtered.size());
					row.add(String.format("%,.2f",
							filtered.stream().mapToDouble(kde -> kde.iskLost()).sum() / 1000000));
					row.add(String.format("%,.2f",
							filtered.stream().mapToDouble(kde -> kde.iskLost()).average().orElse(0.0) / 1000000));
					row.add(
							String.format("%,.2f", filtered.stream().mapToDouble(kde -> kde.iskLost()).min().orElse(0.0) / 1000000));
					row.add(
							String.format("%,.2f", filtered.stream().mapToDouble(kde -> kde.iskLost()).max().orElse(0.0) / 1000000));
				}
			}
			writer.writeNext(row.toArray(String[]::new), false);
		});
		return sw.toString();
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println(generate(
				kde -> kde.getSolarSystem() != null && kde.getSolarSystem().isHS() && kde.getType() != null,
				new TypeFilter(32880), new GroupFilter(463), new GroupFilter(543), new TypeFilter(28606), new GroupFilter(513),
				new GroupFilter(902), new GroupFilter(31)));
	}

}
