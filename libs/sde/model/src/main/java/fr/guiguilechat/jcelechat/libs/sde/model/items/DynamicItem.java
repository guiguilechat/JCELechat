package fr.guiguilechat.jcelechat.libs.sde.model.items;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdynamicItemAttributes;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdynamicItemAttributes.MinMax;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdynamicItemAttributes.TransformTypes;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Mutaplasmid effect
 */
@Getter
@Accessors(fluent = true)
public class DynamicItem extends DataSourced<EdynamicItemAttributes> {

	public static final Mapper<EdynamicItemAttributes, DynamicItem> CACHE = new Mapper<>(
			EdynamicItemAttributes.LOADER.yaml(),
			DynamicItem::new);

	public DynamicItem(DataSource datasource, int id, EdynamicItemAttributes source) {
		super(datasource, id, source);
	}

	protected DynamicItem(int id, EdynamicItemAttributes source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Getter(lazy = true)
	private final Type type = datasource().types().of(id());

	@Getter(lazy = true)
	private final Map<Attribute, MinMax> attributes = makeAttributes();

	protected Map<Attribute, MinMax> makeAttributes() {
		Map<Integer, MinMax> m = source().attributeIDs;
		if (m == null || m.isEmpty()) {
			return Map.of();
		}
		return m.entrySet().stream()
				.collect(Collectors.toMap(
						e -> datasource().attributes().of(e.getKey()),
						Entry::getValue));
	}

	@Getter(lazy = true)
	private final Map<Type, Set<Type>> transformFrom = makeTransformFrom();

	protected Map<Type, Set<Type>> makeTransformFrom() {
		List<TransformTypes> l = source().inputOutputMapping;
		if (l == null || l.isEmpty()) {
			return Map.of();
		}
		Map<Type, Set<Type>> ret = new HashMap<>();
		for (TransformTypes e : l) {
			Type outputType = datasource().types().of(e.resultingType);
			Set<Type> sourceTypes = ret.computeIfAbsent(outputType, _ -> new HashSet<>());
			if (e.applicableTypes != null) {
				e.applicableTypes.stream().map(datasource().types()::of).forEach(sourceTypes::add);
			}
		}
		return ret;
	}

}
