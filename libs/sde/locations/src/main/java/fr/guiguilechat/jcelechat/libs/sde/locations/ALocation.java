package fr.guiguilechat.jcelechat.libs.sde.locations;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class ALocation<T extends InSpace> {

	private final int id;

	private final T source;

	private final Position position;

	protected ALocation(int id, T source) {
		this.id = id;
		this.source = source;
		position = source.position;
	}

	protected abstract String makeEnName();

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final String enName = makeEnName();


}
