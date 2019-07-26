package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.time.LocalDateTime;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_gender;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsIntHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Informations {

	private final ESIAccount con;

	public Informations(ESIAccount con) {
		this.con = con;
	}

	public ObsObjHolder<R_get_characters_character_id> get() {
		return ESIStatic.INSTANCE.cache.characters.get(con.characterId());
	}

	private ObsIntHolder allianceId = null;

	public ObsIntHolder alliance_id() {
		if (allianceId == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (allianceId == null) {
					allianceId = fetch.mapInt(info -> info.alliance_id);
				}
			});
		}
		return allianceId;
	}

	private ObsIntHolder ancestryId = null;

	public ObsIntHolder ancestryId() {
		if (ancestryId == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (ancestryId == null) {
					ancestryId = fetch.mapInt(info -> info.ancestry_id);
				}
			});
		}
		return ancestryId;
	}

	private ObsObjHolder<LocalDateTime> birthday = null;

	public ObsObjHolder<LocalDateTime> birthday() {
		if (birthday == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (birthday == null) {
					birthday = fetch.map(info -> ESITools.convertLocalDateTime(info.birthday));
				}
			});
		}
		return birthday;
	}

	private ObsIntHolder bloodlineId = null;

	public ObsIntHolder bloodlineId() {
		if (bloodlineId == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (bloodlineId == null) {
					bloodlineId = fetch.mapInt(info -> info.bloodline_id);
				}
			});
		}
		return bloodlineId;
	}

	private ObsIntHolder corporationId = null;

	public ObsIntHolder corporationId() {
		if (corporationId == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (corporationId == null) {
					corporationId = fetch.mapInt(info -> info.corporation_id);
				}
			});
		}
		return corporationId;
	}

	private ObsObjHolder<String> description = null;

	public ObsObjHolder<String> description() {
		if (description == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (description == null) {
					description = fetch.map(info -> info.description);
				}
			});
		}
		return description;
	}

	private ObsIntHolder factionId = null;

	public ObsIntHolder factionId() {
		if (factionId == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (factionId == null) {
					factionId = fetch.mapInt(info -> info.faction_id);
				}
			});
		}
		return factionId;
	}

	private ObsObjHolder<get_characters_character_id_gender> gender = null;

	public ObsObjHolder<get_characters_character_id_gender> gender() {
		if (gender == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (gender == null) {
					gender = fetch.map(info -> info.gender);
				}
			});
		}
		return gender;
	}

	private ObsObjHolder<String> name = null;

	public ObsObjHolder<String> name() {
		if (name == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (name == null) {
					name = fetch.map(info -> info.name);
				}
			});
		}
		return name;
	}

	private ObsIntHolder raceId = null;

	public ObsIntHolder raceId() {
		if (raceId == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (raceId == null) {
					raceId = fetch.mapInt(info -> info.race_id);
				}
			});
		}
		return raceId;
	}

	private ObsDoubleHolder securityStatus = null;

	public ObsDoubleHolder securityStatus() {
		if (securityStatus == null) {
			ObsObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (securityStatus == null) {
					securityStatus = fetch.mapDouble(info -> info.security_status);
				}
			});
		}
		return securityStatus;
	}

}
