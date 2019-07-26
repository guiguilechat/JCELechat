package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.time.LocalDateTime;
import java.util.Arrays;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_attributes;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsBoolHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsIntHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Attributes {

	private final ESIAccount con;

	public Attributes(ESIAccount con) {
		this.con = con;
	}

	public ObsObjHolder<R_get_characters_character_id_attributes> get() {
		return con.raw.cache.characters.attributes(con.characterId());
	}

	private ObsIntHolder charisma = null;

	public ObsIntHolder charisma() {
		if (charisma == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (charisma == null) {
					charisma = fetch.mapInt(att->att.charisma);
				}
			});
		}
		return charisma;
	}

	private ObsIntHolder intelligence = null;

	public ObsIntHolder intelligence() {
		if (intelligence == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (intelligence == null) {
					intelligence = fetch.mapInt(att -> att.intelligence);
				}
			});
		}
		return intelligence;
	}

	private ObsIntHolder memory = null;

	public ObsIntHolder memory() {
		if (memory == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (memory == null) {
					memory = fetch.mapInt(att -> att.memory);
				}
			});
		}
		return memory;
	}

	private ObsIntHolder perception = null;

	public ObsIntHolder perception() {
		if (perception == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (perception == null) {
					perception = fetch.mapInt(att -> att.perception);
				}
			});
		}
		return perception;
	}

	private ObsIntHolder willpower = null;

	public ObsIntHolder willpower() {
		if (willpower == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (willpower == null) {
					willpower = fetch.mapInt(att -> att.willpower);
				}
			});
		}
		return willpower;
	}

	private ObsListHolder<Integer> sorted = null;

	public ObsListHolder<Integer> sorted() {
		if (sorted == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				sorted = fetch
						.toList(att -> Arrays.asList(att.charisma, att.intelligence, att.memory, att.perception, att.willpower))
						.sorted(Integer::compareTo);
			});
		}
		return sorted;
	}

	private ObsIntHolder highest = null;

	public ObsIntHolder highest() {
		if (highest == null) {
			ObsListHolder<Integer> sorted = sorted();
			LockWatchDog.BARKER.syncExecute(sorted, () -> {
				if (highest == null) {
					highest = sorted.mapInt(li -> li.get(4));
				}
			});
		}
		return highest;
	}

	private ObsIntHolder sum = null;

	public ObsIntHolder sum() {
		if (sum == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (sum == null) {
					sum = fetch.mapInt(li -> li.charisma + li.intelligence + li.memory + li.perception + li.willpower);
				}
			});
		}
		return sum;
	}

	private ObsBoolHolder accelerated = null;

	public ObsBoolHolder isAccelerated() {
		if (accelerated == null) {
			ObsIntHolder sum = sum();
			LockWatchDog.BARKER.syncExecute(sum, () -> {
				if (accelerated == null) {
					accelerated = sum.test(i -> i > 149);
				}
			});
		}
		return accelerated;
	}

	private ObsIntHolder bonusRemaps = null;

	public ObsIntHolder bonusRemaps() {
		if (bonusRemaps == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (bonusRemaps == null) {
					bonusRemaps = fetch.mapInt(att -> att.bonus_remaps);
				}
			});
		}
		return bonusRemaps;
	}

	private ObsObjHolder<LocalDateTime> lastRemap = null;

	public ObsObjHolder<LocalDateTime> lastRemap() {
		if (lastRemap == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (lastRemap == null) {
					lastRemap = fetch.map(att -> ESITools.convertLocalDateTime(att.last_remap_date));
				}
			});
		}
		return lastRemap;
	}

	private ObsObjHolder<LocalDateTime> remapCoolDown = null;

	public ObsObjHolder<LocalDateTime> remapCoolDown() {
		if (remapCoolDown == null) {
			ObsObjHolder<R_get_characters_character_id_attributes> fetch = get();
			LockWatchDog.BARKER.syncExecute(fetch, () -> {
				if (remapCoolDown == null) {
					remapCoolDown = fetch.map(att -> ESITools.convertLocalDateTime(att.accrued_remap_cooldown_date));
				}
			});
		}
		return remapCoolDown;
	}

	public static int getAttribute(int attID, R_get_characters_character_id_attributes attributes) {
		switch (attID) {
		case 164:
			return attributes.charisma;
		case 165:
			return attributes.intelligence;
		case 166:
			return attributes.memory;
		case 167:
			return attributes.perception;
		case 168:
			return attributes.willpower;
		default:
			return 0;
		}
	}


}
