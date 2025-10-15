package fr.guiguilechat.jcelechat.libs.sde.model.items.generic;

import java.util.Set;

import fr.guiguilechat.jcelechat.libs.sde.model.cache.AIDBasedObject;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.SDEDataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Category;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Group;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Type;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class TypeSet<T> extends AIDBasedObject {

	private final T source;

	public TypeSet(SDEDataSource datasource, int id, T source) {
		super(datasource, id);
		this.source = source;
	}

	protected abstract String makeEnName();

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final String enName = makeEnName();

	public abstract Set<Type> types();

	public abstract Set<Group> groups();

	public abstract Set<Category> categories();

}
