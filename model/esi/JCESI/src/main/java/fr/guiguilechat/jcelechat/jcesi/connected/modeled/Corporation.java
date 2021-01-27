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
import fr.lelouet.collectionholders.interfaces.numbers.ObsBoolHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsIntHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import lombok.Getter;
import lombok.experimental.Accessors;


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

	@Getter(lazy = true)
	private final int id = con.character.infos.corporationId().get();

	// informations

	@Getter(lazy=true)
	private final ObsObjHolder<R_get_corporations_corporation_id> informations = ESIStatic.INSTANCE.cache().corporations
	.get(getId());

	public String getName() {
		return getInformations().get().name;
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsBoolHolder isWarEligible = getInformations().test(info -> info.war_eligible);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsIntHolder memberCount = getInformations().mapInt(info -> info.member_count);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsDoubleHolder taxRate = getInformations().mapDouble(info -> info.tax_rate);

	// divisions

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

	@Getter(lazy = true)
	private final ObsMapHolder<Long, R_get_corporations_corporation_id_structures> structures = con.raw
	.cache().corporations.structures(getId()).toMap(str -> str.structure_id);

	@Getter(lazy = true)
	private final ObsMapHolder<Long, R_get_corporations_corporation_id_facilities> facilities = con.raw
	.cache().corporations.facilities(getId()).toMap(str -> str.facility_id);

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

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, M_get_standings_3> standings = con.raw.cache().corporations.standings(getId())
	.toMap(std -> std.from_id);

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, R_get_corporations_corporation_id_contacts> contacts = con.raw
	.cache().corporations.contacts(getId()).toMap(contact -> contact.contact_id);

	@Getter(lazy = true)
	private final ObsMapHolder<Object, Object> contactsLabels = con.raw.cache().corporations.contacts_labels(getId())
	.toMap(l -> l.label_id, l -> l.label_name);


	//
	// members, titles, roles
	//

	@Getter(lazy = true)
	private final ObsListHolder<Integer> members = con.raw.cache().corporations.members(getId());

	@Getter(lazy = true)
	private final ObsObjHolder<Integer> membersLimit = con.raw.cache().corporations.members_limit(getId());

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, int[]> membersTitles = con.raw.cache().corporations.members_titles(getId())
	.toMap(title -> title.character_id, title -> title.titles);

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, R_get_corporations_corporation_id_titles> titles = con.raw.cache().corporations
	.titles(getId()).toMap(title -> title.title_id);

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, R_get_corporations_corporation_id_membertracking> memberstracking = con.raw
	.cache().corporations.membertracking(getId()).toMap(track -> track.character_id);

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, R_get_corporations_corporation_id_roles> roles = con.raw.cache().corporations
	.roles(getId()).toMap(roles -> roles.character_id);

	@Getter(lazy = true)
	private final ObsListHolder<R_get_corporations_corporation_id_roles_history> rolesHistory = con.raw
	.cache().corporations.roles_history(getId());


	//
	// wars
	//

	/**
	 *
	 * the cached observable list of wars, which started in thelast month, and for
	 * which this corporation is either aggressor or defender.
	 */
	@Getter(lazy = true)
	private final ObsListHolder<R_get_wars_war_id> monthWars = ESIAccess.INSTANCE.wars.getMonthWars()
	.filter(war -> war.aggressor.corporation_id == getId() || war.defender.corporation_id == getId());

}
