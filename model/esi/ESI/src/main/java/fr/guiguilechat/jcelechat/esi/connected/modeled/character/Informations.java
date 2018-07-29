package fr.guiguilechat.jcelechat.esi.connected.modeled.character;

import java.util.concurrent.CountDownLatch;

import fr.guiguilechat.jcelechat.esi.ConnectedImpl;
import fr.guiguilechat.jcelechat.esi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.esi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_characters_character_id;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;

public class Informations {

	private final ESIAccount con;

	private SimpleIntegerProperty allianceId = new SimpleIntegerProperty();

	public ObservableIntegerValue alliance_id() {
		waitData();
		return allianceId;
	}

	private SimpleIntegerProperty ancestryId = new SimpleIntegerProperty();

	public ObservableIntegerValue ancestryId() {
		waitData();
		return ancestryId;
	}

	private SimpleStringProperty birthday = new SimpleStringProperty();

	public ObservableStringValue birthday() {
		waitData();
		return birthday;
	}
	private SimpleIntegerProperty bloodlineId = new SimpleIntegerProperty();

	public ObservableIntegerValue bloodlineId() {
		waitData();
		return bloodlineId;
	}
	private SimpleIntegerProperty corporationId = new SimpleIntegerProperty();

	public ObservableIntegerValue corporationId() {
		waitData();
		return corporationId;
	}
	private SimpleStringProperty description = new SimpleStringProperty();

	public ObservableStringValue description() {
		waitData();
		return description;
	}
	private SimpleIntegerProperty factionId = new SimpleIntegerProperty();

	public ObservableIntegerValue factionId() {
		waitData();
		return factionId;
	}
	private SimpleStringProperty gender = new SimpleStringProperty();

	public ObservableStringValue gender() {
		waitData();
		return gender;
	}
	private SimpleStringProperty name = new SimpleStringProperty();

	public ObservableStringValue name() {
		waitData();
		return name;
	}
	private SimpleIntegerProperty raceId = new SimpleIntegerProperty();

	public ObservableIntegerValue raceId() {
		waitData();
		return raceId;
	}
	private SimpleDoubleProperty securityStatus = new SimpleDoubleProperty();

	public ObservableDoubleValue securityStatus() {
		waitData();
		return securityStatus;
	}

	public Informations(ESIAccount con) {
		this.con = con;
	}

	boolean connected = false;
	CountDownLatch dataLatch = null;

	protected void waitData() {
		if (!connected) {
			synchronized (this) {
				if (!connected) {
					dataLatch = new CountDownLatch(1);
					ConnectedImpl.listenO(ESIStatic.INSTANCE.cache.characters.get(con.characterId()), this::handleData);
					connected = true;
				}
			}
		}
		try {
			dataLatch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public synchronized void handleData(Object o, R_get_characters_character_id old, R_get_characters_character_id info) {
		allianceId.set(info.alliance_id);
		ancestryId.set(info.ancestry_id);
		birthday.set(info.birthday);
		bloodlineId.set(info.bloodline_id);
		corporationId.set(info.corporation_id);
		description.set(info.description);
		factionId.set(info.faction_id);
		gender.set(info.gender);
		name.set(info.name);
		raceId.set(info.race_id);
		securityStatus.set(info.security_status);
		dataLatch.countDown();
	}

}
