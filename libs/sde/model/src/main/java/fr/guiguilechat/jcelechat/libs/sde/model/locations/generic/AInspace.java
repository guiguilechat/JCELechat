package fr.guiguilechat.jcelechat.libs.sde.model.locations.generic;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * represent an {@link InSpace}.
 * A name , a position, and source data. Sub classes can present more
 * information.
 *
 * @param <T>
 */
@Getter
@Accessors(fluent = true)
public abstract class AInspace<T> extends AIDBasedObject {

	private final T source;

	protected AInspace(int id, T source) {
		super(id);
		this.source = source;
	}

	protected abstract String makeEnName();

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final String enName = makeEnName();

	protected abstract Position makePosition();

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final Position position = makePosition();

	@Override
	public String toString() {
		return enName();
	}


}
