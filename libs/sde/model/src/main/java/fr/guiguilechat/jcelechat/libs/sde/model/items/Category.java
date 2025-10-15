package fr.guiguilechat.jcelechat.libs.sde.model.items;

import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Ecategories;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.LocalCacheDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.NamingMapper;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.SDEDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.items.generic.TypeSet;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Category extends TypeSet<Ecategories> {

	public static final NamingMapper<Ecategories, Category> CACHE = new NamingMapper<>(Ecategories.LOADER,
			Category::new, TypeSet::enName);

	private final boolean published;
	private final int iconId;

	public Category(SDEDataSource datasource, int id, Ecategories source) {
		super(datasource, id, source);
		published = source.published;
		iconId = source.iconID;
	}

	public Category(int id, Ecategories source) {
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
	private final Set<Category> categories = Set.of(this);

	@Getter(lazy = true)
	private final Set<Group> groups = datasource().groups().all().stream()
			.filter(g -> equals(g.category()))
			.collect(Collectors.toSet());

	@Getter(lazy = true)
	private final Set<Type> types = groups().stream().flatMap(g -> g.types().stream())
			.collect(Collectors.toSet());

}
