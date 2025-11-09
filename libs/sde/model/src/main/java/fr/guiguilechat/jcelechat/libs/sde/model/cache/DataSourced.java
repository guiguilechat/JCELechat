package fr.guiguilechat.jcelechat.libs.sde.model.cache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * an object obtained from a datasource, for which {@link #equals(Object)} and
 * {@link #hashCode()} are based on its id
 */
@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public class DataSourced<SourceType> {

	private final DataSource datasource;

	private final int id;

	private final SourceType source;

	@Override
	public boolean equals(Object obj) {
		return obj != null
				&& obj.getClass().equals(getClass())
				&& ((DataSourced<?>) obj).id == id;
	}

	@Override
	public int hashCode() {
		return id;
	}

}
