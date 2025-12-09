package fr.guiguilechat.jcelechat.libs.sde.model.items.generic;

import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Category;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Group;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Type;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * An {@link DataSourced} that refers to a set of types, their groups, and
 * categories. Common class for type, group, category, and market group
 *
 * @param <SourceType>
 */
@Getter
@Accessors(fluent = true)
public abstract class TypeSet<SourceType> extends DataSourced<SourceType> {

	public TypeSet(DataSource datasource, int id, SourceType source) {
		super(datasource, id, source);
	}

	protected abstract String makeEnName();

	@Getter(lazy = true)
	private final String enName = makeEnName();

	public abstract Set<Type> types();

	public abstract Set<Group> groups();

	public abstract Set<Category> categories();

}
