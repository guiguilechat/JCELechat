package fr.guiguilechat.jcelechat.libs.spring.gameclient.updater;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;

/** interface for the beans that should react to game client update */
public interface GameClientUpdateListener {

	default List<String> listGameClientCaches() {
		return List.of();
	}

	default void beforeGameClientUpdate() {

	}

	default void afterGameClientUpdate() {

	}

	default void onGameClientUpdate(ClientCache cache) {

	}

}