package fr.guiguilechat.jcelechat.libs.sde.model.locations.generic;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.InSpace;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * represent an {@link InSpace} : A name , a position, and source data. Sub
 * classes can present more
 * information.
 *
 * @param <SourceType>
 */
@Getter
@Accessors(fluent = true)
public abstract class ASpace<SourceType> extends DataSourced<SourceType> {

	protected ASpace(DataSource datasource, int id, SourceType source) {
		super(datasource, id, source);
	}

	protected abstract String makeEnName();

	@Getter(lazy = true)
	private final String enName = makeEnName();

	protected abstract Position makePosition();

	/**
	 * absolute position. The position of some items is stored relatively, so this
	 * method adds the center to it.
	 */
	@Getter(lazy = true)
	private final Position position = makePosition();

	@Override
	public String toString() {
		return enName();
	}


}
