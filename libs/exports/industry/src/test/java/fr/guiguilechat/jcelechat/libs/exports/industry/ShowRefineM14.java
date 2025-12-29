package fr.guiguilechat.jcelechat.libs.exports.industry;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.types.Module;

public class ShowRefineM14 {

	public static void main(String[] args) {
		MetaLevelOld mlo = MetaLevelOld.INSTANCE;
		Collection<Module> modules = Module.METACAT.load().values().stream()
				.filter(m -> m.marketGroup != 0 && m.published && mlo.value(m) < 5 && mlo.value(m) > 0)
				.sorted(Comparator.comparing(t -> t.name))
				.collect(Collectors.toList());
		int[] matids = modules.stream()
				.map(m -> IndustryUsage.of(m.id))
				.filter(iu -> iu != null && iu.reprocessInto != null && !iu.reprocessInto.isEmpty())
				.flatMapToInt(iu -> iu.reprocessInto.entrySet().stream().filter(e -> e.getValue() > 0)
						.mapToInt(Entry::getKey))
				.distinct().sorted().toArray();

		System.out.println("\t\t\ttypeid\t"
				+ IntStream.of(matids).mapToObj(TypeIndex::getType).map(t -> "" + t.id).collect(Collectors.joining("\t")));
		System.out.println("\t\t\ttype\t"
				+ IntStream.of(matids).mapToObj(TypeIndex::getType).map(t -> "" + t.name).collect(Collectors.joining("\t")));
		System.out.println("name\tid\tmeta");
		for (Module m : modules) {
			IndustryUsage iu = IndustryUsage.of(m.id);
			System.out.println("\'" + m.name + "\t" + m.id + "\t" + mlo.value(m) + "\t\t"
					+ IntStream.of(matids).mapToObj(id -> "" + 0.5 * iu.reprocessInto.getOrDefault(id, 0.0))
					.collect(Collectors.joining("\t")));
		}
	}

}
