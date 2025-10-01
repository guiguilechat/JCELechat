package fr.guiguilechat.jcelechat.libs.sde.model.locations.generic;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * represent an {@link InSpace}
 *
 * @param <T>
 */
@Getter
@Accessors(fluent = true)
public abstract class AInspace<T extends InSpace> extends AIDBasedObject {

	private final T source;

	private final Position position;

	protected AInspace(int id, T source) {
		super(id);
		this.source = source;
		position = source.position;
	}

	protected abstract String makeEnName();

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final String enName = makeEnName();


}
