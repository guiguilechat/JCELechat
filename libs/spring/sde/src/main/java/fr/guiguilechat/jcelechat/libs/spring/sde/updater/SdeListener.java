package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

/**
 * interface for the beans that should react to SDE update. If a Bean implements
 * this interface, upon receiving a new SDE the following methods are called :
 * <ol>
 * <li>{@link #beforeSdeUpdate()} on each of them to prepare cleanup</li>
 * <li>{@link #onSdeFile(String, Supplier)} for each sde entry, on each of
 * them</li>
 * <li>{@link #afterSdeUpdate()} on each of them to trigger post-creation
 * analysis</li>
 * <li>{@link #listSDECaches()} on each of them to delete corresponding
 * caches, typically logging the absence of the required files</li>
 * </ol>
 */
public interface SdeListener {

	default void beforeSdeUpdate() {

	}

	default void onSdeFile(String entryName, Supplier<InputStream> fileContent) {

	}

	default void afterSdeUpdate() {

	}

	default List<String> listSDECaches() {
		return List.of();
	}
}