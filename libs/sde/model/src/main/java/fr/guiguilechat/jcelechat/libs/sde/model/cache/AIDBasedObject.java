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
public class AIDBasedObject {

	private final SDEDataSource datasource;

	private final int id;

	@Override
	public boolean equals(Object obj) {
		return obj != null
				&& obj.getClass().equals(getClass())
				&& ((AIDBasedObject) obj).id == id;
	}

	@Override
	public int hashCode() {
		return id;
	}

}
