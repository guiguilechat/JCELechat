package fr.guiguilechat.jcelechat.libs.mer.killdump.filters;

import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDEntry;
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

	public static final TypeFilter ORCA = new TypeFilter(28606);
	public static final TypeFilter VENTURE = new TypeFilter(32880);

	public static final TypeFilter RAITARU = new TypeFilter(35825);
	public static final TypeFilter AZBEL = new TypeFilter(35826);
	public static final TypeFilter SOTIYO = new TypeFilter(35827);
	public static final TypeFilter ASTRAHUS = new TypeFilter(35832);
	public static final TypeFilter FORTIZAR = new TypeFilter(35833);
	public static final TypeFilter KEEPSTAR = new TypeFilter(35834);
	public static final TypeFilter ATHANOR = new TypeFilter(35835);
	public static final TypeFilter TATARA = new TypeFilter(35836);

}