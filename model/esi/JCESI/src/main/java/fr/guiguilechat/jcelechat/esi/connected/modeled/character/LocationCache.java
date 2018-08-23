package fr.guiguilechat.jcelechat.esi.connected.modeled.character;

import java.util.concurrent.CountDownLatch;

import fr.guiguilechat.jcelechat.esi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_characters_character_id_location;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableLongValue;

/**
 * fetch the location of the player once the cache is expired.
 *
 */
public class LocationCache {

	public final ESIAccount account;

	public LocationCache(ESIAccount acc) {
		account = acc;
		acc.raw.cache.addFetchCacheObject(account.characterName() + ".locations",
				h -> acc.raw.get_characters_location(acc.characterId(), h),
				this::handleNewCache);
	}

	private final CountDownLatch rdyData = new CountDownLatch(1);

	public void waitData() throws InterruptedException {
		rdyData.await();
	}

	public SimpleIntegerProperty solarSystem = new SimpleIntegerProperty();

	public ObservableIntegerValue getSolarSystemID() {
		return solarSystem;
	}

	public SimpleIntegerProperty stationID = new SimpleIntegerProperty();

	public ObservableIntegerValue getStationID() {
		return stationID;
	}

	public SimpleLongProperty structureID = new SimpleLongProperty();

	public ObservableLongValue getStructureID() {
		return structureID;
	}

	public void handleNewCache(R_get_characters_character_id_location newLocation) {
		if (newLocation == null) {
			return;
		}
		solarSystem.set(newLocation.solar_system_id);
		stationID.set(newLocation.station_id);
		structureID.set(newLocation.structure_id);
		rdyData.countDown();
	}

}
