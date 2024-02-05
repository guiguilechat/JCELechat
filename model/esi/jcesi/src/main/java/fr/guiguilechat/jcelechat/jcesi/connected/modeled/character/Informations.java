package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.time.LocalDateTime;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_gender;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import fr.lelouet.tools.holders.interfaces.numbers.IntHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Informations {

	private final ESIAccount con;

	public Informations(ESIAccount con) {
		this.con = con;
	}

	public ObjHolder<R_get_characters_character_id> get() {
		return ESIStatic.INSTANCE.cache().characters.get(con.characterId());
	}

	private IntHolder allianceId = null;

	public IntHolder alliance_id() {
		if (allianceId == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (allianceId == null) {
					allianceId = fetch.mapInt(info -> info.alliance_id);
				}
			});
		}
		return allianceId;
	}

	private ObjHolder<LocalDateTime> birthday = null;

	public ObjHolder<LocalDateTime> birthday() {
		if (birthday == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (birthday == null) {
					birthday = fetch.map(info -> ESITools.fieldLocalDateTime(info.birthday));
				}
			});
		}
		return birthday;
	}

	private IntHolder bloodlineId = null;

	public IntHolder bloodlineId() {
		if (bloodlineId == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (bloodlineId == null) {
					bloodlineId = fetch.mapInt(info -> info.bloodline_id);
				}
			});
		}
		return bloodlineId;
	}

	private IntHolder corporationId = null;

	public IntHolder corporationId() {
		if (corporationId == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (corporationId == null) {
					corporationId = fetch.mapInt(info -> (info != null ? info.corporation_id : -1));
				}
			});
		}
		return corporationId;
	}

	private ObjHolder<String> description = null;

	public ObjHolder<String> description() {
		if (description == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (description == null) {
					description = fetch.map(info -> info.description);
				}
			});
		}
		return description;
	}

	private IntHolder factionId = null;

	public IntHolder factionId() {
		if (factionId == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (factionId == null) {
					factionId = fetch.mapInt(info -> info.faction_id);
				}
			});
		}
		return factionId;
	}

	private ObjHolder<get_characters_character_id_gender> gender = null;

	public ObjHolder<get_characters_character_id_gender> gender() {
		if (gender == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (gender == null) {
					gender = fetch.map(info -> info.gender);
				}
			});
		}
		return gender;
	}

	private ObjHolder<String> name = null;

	public ObjHolder<String> name() {
		if (name == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (name == null) {
					name = fetch.map(info -> info.name);
				}
			});
		}
		return name;
	}

	private IntHolder raceId = null;

	public IntHolder raceId() {
		if (raceId == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (raceId == null) {
					raceId = fetch.mapInt(info -> info.race_id);
				}
			});
		}
		return raceId;
	}

	private DoubleHolder securityStatus = null;

	public DoubleHolder securityStatus() {
		if (securityStatus == null) {
			ObjHolder<R_get_characters_character_id> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (securityStatus == null) {
					securityStatus = fetch.mapDouble(info -> info.security_status);
				}
			});
		}
		return securityStatus;
	}

}
