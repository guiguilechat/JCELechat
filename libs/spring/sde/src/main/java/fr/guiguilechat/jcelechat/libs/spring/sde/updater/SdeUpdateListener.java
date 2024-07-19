package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

/** interface for the beans that should react to SDE update */
public interface SdeUpdateListener {

	public default List<String> listSDECaches() {
		return List.of();
	}

	public default void beforeSdeUpdate() {

	}

	public default void onSdeFile(String entryName, Supplier<InputStream> fileContent) {

	}

	public default void afterSdeUpdate() {

	}
}