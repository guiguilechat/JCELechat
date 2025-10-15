package fr.guiguilechat.jcelechat.libs.sde.model.items;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmarketGroups;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.items.generic.TypeSet;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class MarketGroup extends TypeSet<EmarketGroups> {

	public static final Mapper<EmarketGroups, MarketGroup> CACHE = new Mapper<>(EmarketGroups.LOADER,
			MarketGroup::new);

	private final boolean hasTypes;
	private final int iconId;

	public MarketGroup(DataSource datasource, int id, EmarketGroups source) {
		super(datasource, id, source);
		hasTypes = source.hasTypes;
		iconId = source.iconID;
	}

	public MarketGroup(int id, EmarketGroups source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Override
	protected String makeEnName() {
		return source().enName();
	}

	@Getter(lazy = true)
	private final String enDescription = source().enDescription();

	@Getter(lazy = true)
	private final MarketGroup parent = datasource().marketGroups().of(source().parentGroupID);

	/**
	 * those of which this is the parent
	 */
	@Getter(lazy = true)
	private final Set<MarketGroup> children = datasource().marketGroups().all().stream()
			.filter(mg -> this.equals(mg.parent()))
			.collect(Collectors.toSet());

	/**
	 * children and their children
	 */
	@Getter(lazy = true)
	private final Set<MarketGroup> descendants = streamDescendants().collect(Collectors.toSet());

	protected Stream<MarketGroup> streamDescendants() {
		return children().stream().flatMap(mg -> Stream.concat(
				Stream.of(mg),
				mg.streamDescendants()));
	}

	/**
	 * all types of this and its descendants
	 */
	@Getter(lazy = true)
	private final Set<Type> types = datasource().types().all().stream()
			.filter(t -> equals(t.marketGroup())
					|| descendants().contains(t.marketGroup()))
			.collect(Collectors.toSet());

	@Getter(lazy = true)
	private final Set<Group> groups = types().stream()
			.map(Type::group)
			.collect(Collectors.toSet());

	@Getter(lazy = true)
	private final Set<Category> categories = groups().stream()
			.map(Group::category)
			.collect(Collectors.toSet());

}
