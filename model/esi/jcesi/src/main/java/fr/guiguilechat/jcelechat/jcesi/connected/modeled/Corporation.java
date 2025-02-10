package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Assets;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Industry;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Market;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Wallet;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.*;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import fr.lelouet.tools.holders.interfaces.numbers.BoolHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import fr.lelouet.tools.holders.interfaces.numbers.IntHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import lombok.Getter;
import lombok.experimental.Accessors;


public class Corporation {

	@SuppressWarnings("unused")
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Corporation.class);

	public final ESIAccount con;
	public final Wallet wallet;
	public final Market market;
	public final Industry industry;

	public Corporation(ESIAccount con) {
		this.con = con;
		assets = new Assets(con);
		wallet = new Wallet(this);
		market = new Market(this);
		industry = new Industry(con);
	}

	@Getter(lazy = true)
	private final int id = con.character.infos.corporationId().get();

	// informations

	@Getter(lazy = true)
	private final ObjHolder<R_get_corporations_corporation_id> informations = ESIRawPublic.INSTANCE.cache().corporations
	.get(getId());

	public String getName() {
		return getInformations().get().name;
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isWarEligible = getInformations().test(info -> info.war_eligible);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder memberCount = getInformations().mapInt(info -> info.member_count);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final DoubleHolder taxRate = getInformations().mapDouble(info -> info.tax_rate);

	// divisions

	public ObjHolder<R_get_corporations_corporation_id_divisions> getDivisions() {
		return con.connection().cache().corporations.divisions(getId());
	}

	//
	// assets
	//

	public final Assets assets;

	//
	// structures and facilities
	//

	@Getter(lazy = true)
	private final MapHolder<Long, R_get_corporations_corporation_id_structures> structures = con.connection()
	.cache().corporations.structures(getId()).toMap(str -> str.structure_id);

	@Getter(lazy = true)
	private final MapHolder<Long, R_get_corporations_corporation_id_facilities> facilities = con.connection()
	.cache().corporations.facilities(getId()).toMap(str -> str.facility_id);

	//
	// journal
	//

	private Map<Integer, MapHolder<Long, M_get_journal_13>> cachedJournals = new HashMap<>();

	public MapHolder<Long, M_get_journal_13> getJournal(int division_id) {
		if (cachedJournals.get(division_id) == null) {
			LockWatchDog.BARKER.syncExecute(cachedJournals, () -> {
				if (cachedJournals.get(division_id) == null) {
					cachedJournals.put(division_id,
							con.connection().cache().corporations.wallets_journal(getId(), division_id).toMap(j -> j.id));
				}
			});
		}
		return cachedJournals.get(division_id);
	}

	//
	// standings, contacts, labels
	//

	@Getter(lazy = true)
	private final MapHolder<Integer, M_get_standings_3> standings = con.connection().cache().corporations
	.standings(getId())
	.toMap(std -> std.from_id);

	@Getter(lazy = true)
	private final MapHolder<Integer, R_get_corporations_corporation_id_contacts> contacts = con.connection()
	.cache().corporations.contacts(getId()).toMap(contact -> contact.contact_id);

	@Getter(lazy = true)
	private final MapHolder<Object, Object> contactsLabels = con.connection().cache().corporations
	.contacts_labels(getId())
	.toMap(l -> l.label_id, l -> l.label_name);


	//
	// members, titles, roles
	//

	@Getter(lazy = true)
	private final ListHolder<Integer> members = con.connection().cache().corporations.members(getId());

	@Getter(lazy = true)
	private final ObjHolder<Integer> membersLimit = con.connection().cache().corporations.members_limit(getId());

	@Getter(lazy = true)
	private final MapHolder<Integer, int[]> membersTitles = con.connection().cache().corporations
	.members_titles(getId())
	.toMap(title -> title.character_id, title -> title.titles);

	@Getter(lazy = true)
	private final MapHolder<Integer, R_get_corporations_corporation_id_titles> titles = con.connection()
	.cache().corporations
	.titles(getId()).toMap(title -> title.title_id);

	@Getter(lazy = true)
	private final MapHolder<Integer, R_get_corporations_corporation_id_membertracking> memberstracking = con
	.connection()
	.cache().corporations.membertracking(getId()).toMap(track -> track.character_id);

	@Getter(lazy = true)
	private final MapHolder<Integer, R_get_corporations_corporation_id_roles> roles = con.connection()
	.cache().corporations
	.roles(getId()).toMap(roles -> roles.character_id);

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_roles_history> rolesHistory = con.connection()
	.cache().corporations.roles_history(getId());


	//
	// wars
	//

	/**
	 *
	 * the cached observable list of wars, which started in the last month, and
	 * for which this corporation is either aggressor or defender.
	 */
	@Getter(lazy = true)
	private final ListHolder<R_get_wars_war_id> monthWars = ESIAccess.INSTANCE.wars.getMonthWars()
	.filter(war -> war.aggressor.corporation_id == getId() || war.defender.corporation_id == getId());

	/**
	 *
	 * the cached observable list of wars, which started in the last month, and
	 * for which this corporation is either aggressor or defender.
	 */
	@Getter(lazy = true)
	private final ListHolder<R_get_wars_war_id> allWars = ESIAccess.INSTANCE.wars.getAllWars()
	.filter(war -> war.aggressor.corporation_id == getId() || war.defender.corporation_id == getId());

}
