package fr.guiguilechat.jcelechat.libs.mer.killdump.filters;

import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDParser.KDEntry;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;

public class TypeFilter implements Predicate<KDEntry> {

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