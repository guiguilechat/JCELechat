package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Assets;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.CorpBookmarks;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Industry;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Market;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Wallet;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_ICOAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_divisions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_membertracking;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_roles_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_structures;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_titles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Corporation {

	@SuppressWarnings("unused")
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Corporation.class);

	public final ESIAccount con;

	public final CorpBookmarks bms;
	public final Wallet wallet;
	public final Market market;
	public final Industry industry;

	public Corporation(ESIAccount con) {
		this.con = con;
		assets = new Assets(con);
		bms = new CorpBookmarks(con);
		wallet = new Wallet(this);
		market = new Market(this);
		industry = new Industry(con);
	}

	public int getId() {
		int ret = con.character.infos.corporationId().get();
		return ret;
	}

	public ObsObjHolder<R_get_corporations_corporation_id> getInformations() {
		return ESIStatic.INSTANCE.cache().corporations.get(getId());
	}

	public String getName() {
		return getInformations().get().name;
	}

	public ObsObjHolder<R_get_corporations_corporation_id_divisions> getDivisions() {
		return con.raw.cache().corporations.divisions(getId());
	}

	//
	// assets
	//

	public final Assets assets;

	//
	// structures and facilities
	//

	private ObsMapHolder<Long, R_get_corporations_corporation_id_structures> cachedStructures = null;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_structures> getStructures() {
		if (cachedStructures == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedStructures == null) {
					cachedStructures = con.raw.cache().corporations.structures(getId()).toMap(str -> str.structure_id);
				}
			});
		}
		return cachedStructures;
	}

	private ObsMapHolder<Long, R_get_corporations_corporation_id_facilities> cachedFacilities;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_facilities> getFacilities() {
		if (cachedFacilities == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedFacilities == null) {
					cachedFacilities = con.raw.cache().corporations.facilities(getId()).toMap(str -> str.facility_id);
				}
			});
		}
		return cachedFacilities;
	}

	//
	// journal
	//

	private Map<Integer, ObsMapHolder<Long, M_get_journal_13>> cachedJournals = new HashMap<>();

	public ObsMapHolder<Long, M_get_journal_13> getJournal(int division_id) {
		if (cachedJournals.get(division_id) == null) {
			LockWatchDog.BARKER.syncExecute(cachedJournals, () -> {
				if (cachedJournals.get(division_id) == null) {
					cachedJournals.put(division_id,
							con.raw.cache().corporations.wallets_journal(getId(), division_id).toMap(j -> j.id));
				}
			});
		}
		return cachedJournals.get(division_id);
	}

	//
	// standings, contacts, labels
	//

	private ObsMapHolder<Integer, M_get_standings_3> cachedStandings;

	public ObsMapHolder<Integer, M_get_standings_3> getStandings() {
		if (cachedStandings == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedStandings == null) {
					cachedStandings = con.raw.cache().corporations.standings(getId()).toMap(std -> std.from_id);
				}
			});
		}
		return cachedStandings;
	}

	private ObsMapHolder<Integer, R_get_corporations_corporation_id_contacts> cachedContacts = null;

	public ObsMapHolder<Integer, R_get_corporations_corporation_id_contacts> contacts() {
		if (cachedContacts == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedContacts == null) {
					cachedContacts = con.raw.cache().corporations.contacts(getId()).toMap(contact -> contact.contact_id);
				}
			});
		}
		return cachedContacts;
	}

	private ObsMapHolder<Object, Object> cachedContacts_labels = null;

	public ObsMapHolder<Object, Object> contact_labels() {
		if (cachedContacts_labels == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedContacts_labels == null) {
					cachedContacts_labels = con.raw.cache().corporations.contacts_labels(getId()).toMap(l -> l.label_id,
							l -> l.label_name);
				}
			});
		}
		return cachedContacts_labels;
	}

	//
	// members, titles, roles
	//

	public ObsListHolder<Integer> getMembers() {
		return con.raw.cache().corporations.members(getId());
	}

	public ObsObjHolder<Integer> getMembersLimit() {
		return con.raw.cache().corporations.members_limit(getId());
	}

	private ObsMapHolder<Integer, int[]> cachedMembersTitles = null;

	public ObsMapHolder<Integer, int[]> getMemberTitles() {
		if (cachedMembersTitles == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedMembersTitles == null) {
					cachedMembersTitles = con.raw.cache().corporations.members_titles(getId()).toMap(title -> title.character_id,
							title -> title.titles);
				}
			});
		}
		return cachedMembersTitles;
	}

	private ObsMapHolder<Integer, R_get_corporations_corporation_id_titles> cachedTitles = null;

	public ObsMapHolder<Integer, R_get_corporations_corporation_id_titles> getTitles() {
		if (cachedTitles == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedTitles == null) {
					cachedTitles = con.raw.cache().corporations.titles(getId()).toMap(title -> title.title_id);
				}
			});
		}
		return cachedTitles;
	}

	private ObsMapHolder<Integer, R_get_corporations_corporation_id_membertracking> cachedMemberstracking = null;

	public ObsMapHolder<Integer, R_get_corporations_corporation_id_membertracking> getMembersTracking() {
		if (cachedMemberstracking == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedMemberstracking == null) {
					cachedMemberstracking = con.raw.cache().corporations.membertracking(getId())
							.toMap(track -> track.character_id);
				}
			});
		}
		return cachedMemberstracking;
	}

	private ObsMapHolder<Integer, R_get_corporations_corporation_id_roles> cachedRoles;

	/**
	 * @see G_ICOAccess#get_corporations_roles(int, Map)
	 * @return
	 */
	public ObsMapHolder<Integer, R_get_corporations_corporation_id_roles> getRoles() {
		if (cachedRoles == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedRoles == null) {
					cachedRoles = con.raw.cache().corporations.roles(getId()).toMap(roles -> roles.character_id);
				}
			});
		}
		return cachedRoles;
	}

	/**
	 * @see G_ICOAccess#get_corporations_roles_history(int, Integer, Map)
	 * @return
	 */
	public ObsListHolder<R_get_corporations_corporation_id_roles_history> getRolesHistory() {
		// already cached for direct resource
		return con.raw.cache().corporations.roles_history(getId());
	}

	//
	// wars
	//

	private ObsListHolder<R_get_wars_war_id> cachedMonthWars = null;

	/**
	 *
	 * @return the cached observable list of wars, which started in thelast month,
	 *         and for which this corporation is either aggressor or defender.
	 */
	public ObsListHolder<R_get_wars_war_id> getMonthWars() {
		if (cachedMonthWars == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedMonthWars == null) {
					cachedMonthWars = ESIAccess.INSTANCE.wars.getMonthWars()
							.filter(war -> war.aggressor.corporation_id == getId() || war.defender.corporation_id == getId());
				}
			});
		}
		return cachedMonthWars;
	}

}
