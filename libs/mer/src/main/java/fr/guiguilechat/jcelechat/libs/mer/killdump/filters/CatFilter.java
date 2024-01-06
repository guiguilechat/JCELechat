package fr.guiguilechat.jcelechat.libs.mer.killdump.filters;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDEntry;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;

public class CatFilter implements Predicate<KDEntry> {

	protected static Map<Integer, String> catToName = makeCatToName();

	protected static Map<Integer, String> makeCatToName() {
		return Stream.of(IMetaCategory.INSTANCES)
				.collect(Collectors.toMap(imc -> imc.getCategoryId(), imc -> imc.getName()));
	}

	protected final int catId;

	public CatFilter(int catId) {
		this.catId = catId;
	}

	@Override
	public boolean test(KDEntry t) {
		return t.getType() != null && t.getType().getCategory().getCategoryId() == catId;
	}

	@Override
	public String toString() {
		return catToName.getOrDefault(catId, "c_" + catId);
	}

	public static final CatFilter STRUCTURE = new CatFilter(65);

}