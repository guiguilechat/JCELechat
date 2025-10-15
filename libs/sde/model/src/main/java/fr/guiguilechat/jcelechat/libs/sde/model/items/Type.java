package fr.guiguilechat.jcelechat.libs.sde.model.items;

import java.math.BigDecimal;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.LocalCacheDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.SDEDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.items.generic.TypeSet;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Type extends TypeSet<Etypes> {

	public static final NamingMapper<Etypes, Type> CACHE = new NamingMapper<>(Etypes.LOADER,
			Type::new, TypeSet::enName);

	private final BigDecimal basePrice;
	private final BigDecimal capacity;
	private final int factionId;
	private final int graphicId;
	private final int iconId;
	private final BigDecimal mass;
//	private final int metaGroupId;
	private final int portionSize;
	private final boolean published;
//	private final int raceId;
	private final BigDecimal radius;
	private final int soundId;
	private final BigDecimal volume;

	public Type(SDEDataSource datasource, int id, Etypes source) {
		super(datasource, id, source);
		basePrice = source.basePrice;
		capacity = source.capacity;
		factionId = source.factionID;
		graphicId = source.graphicID;
		iconId = source.iconID;
		mass = source.mass;
		portionSize = source.portionSize;
		published = source.published;
		radius = source.radius;
		soundId = source.soundID;
		volume = source.volume;
	}

	public Type(int id, Etypes source) {
		this(LocalCacheDataSource.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return source().enName();
	}

	@Override
	public String toString() {
		return enName() + "(" + id() + ")";
	}

	@Getter(lazy = true)
	private final Group group = datasource().groups().of(source().groupID);

	@Getter(lazy = true)
	private final MarketGroup marketGroup = datasource().marketGroups().of(source().marketGroupID);

	@Getter(lazy = true)
	private final Type variationParent = source().variationParentTypeID == null ? null
			: datasource().types().of(source().variationParentTypeID);

	@Getter(lazy = true)
	private final Set<Type> types = Set.of(this);

	@Getter(lazy=true)
	private final Set<Group> groups = Set.of(group());

	@Getter(lazy = true)
	private final Set<Category> categories = Set.of(group().category());

}
