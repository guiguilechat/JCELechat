package fr.guiguilechat.jcelechat.libs.sde.model.items;

import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Egroups;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.items.generic.TypeSet;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Group extends TypeSet<Egroups> {

	public static final NamingMapper<Egroups, Group> CACHE = new NamingMapper<>(Egroups.LOADER.yaml(),
			Group::new, TypeSet::enName);

	private final boolean anchorable;
	private final boolean anchored;
	private final boolean fittableNonSingleton;
	private final int iconId;
	private final boolean published;
	private final boolean useBasePrice;

	public Group(DataSource datasource, int id, Egroups source) {
		super(datasource, id, source);
		anchorable = source.anchorable;
		anchored = source.anchored;
		fittableNonSingleton = source.fittableNonSingleton;
		iconId = source.iconID;
		published = source.published;
		useBasePrice = source.useBasePrice;
	}

	public Group(int id, Egroups source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
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
	private final Set<Type> types = datasource().types().all().stream()
			.filter(t -> equals(t.group()))
			.collect(Collectors.toSet());

	@Getter(lazy = true)
	private final Set<Group> groups = Set.of(this);

	@Getter(lazy = true)
	private final Category category = datasource().categories().of(source().categoryID);

	@Getter(lazy = true)
	private final Set<Category> categories = Set.of(category());

}
