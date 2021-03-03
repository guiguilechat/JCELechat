package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Assets;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Attributes;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.CharBookmarks;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Industry;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Informations;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Location;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Market;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Notifications;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.PI;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Route;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Skills;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Wallet;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_loyalty_points;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_online;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_base;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_hq;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_other;
import fr.lelouet.collectionholders.impl.numbers.ObsDoubleHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsBoolHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsIntHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class EveCharacter {

	@SuppressWarnings("unused")
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EveCharacter.class);

	protected final ESIAccount con;

	public final Attributes attributes;

	public final CharBookmarks bms;

	public final Industry industry;

	public final Informations infos;

	public final Location location;

	public final Market market;

	public final Notifications notifications;

	public final Skills skills;

	public final Wallet wallet;

	public final PI pi;

	public final Route route;

	public EveCharacter(ESIAccount con) {
		this.con = con;
		attributes = new Attributes(con);
		assets = new Assets(con);
		bms = new CharBookmarks(con);
		industry = new Industry(con);
		infos = new Informations(con);
		location = new Location(con);
		market = new Market(con);
		notifications = new Notifications(con);
		skills = new Skills(con);
		wallet = new Wallet(con);
		pi = new PI(con);
		route = new Route(con);
	}

	public String getName() {
		return con.name();
	}

	//
	// roles
	//

	@Getter(lazy = true)
	private final ObsObjHolder<R_get_characters_character_id_roles> allRoles = con.connection().cache().characters
	.roles(con.characterId());

	@Getter(lazy = true)
	private final ObsSetHolder<get_characters_character_id_roles_roles> roles = getAllRoles()
	.toList(roles -> Arrays.asList(roles.roles)).distinct();

	@Getter(lazy = true)
	private final ObsSetHolder<get_characters_character_id_roles_roles_at_hq> rolesHQ = getAllRoles()
	.toList(roles -> Arrays.asList(roles.roles_at_hq)).distinct();

	@Getter(lazy = true)
	private final ObsSetHolder<get_characters_character_id_roles_roles_at_base> rolesBase = getAllRoles()
	.toList(roles -> Arrays.asList(roles.roles_at_base)).distinct();

	@Getter(lazy = true)
	private final ObsSetHolder<get_characters_character_id_roles_roles_at_other> rolesOther = getAllRoles()
	.toList(roles -> Arrays.asList(roles.roles_at_other)).distinct();

	//
	// online state
	//

	@Getter(lazy = true)
	private final ObsObjHolder<R_get_characters_character_id_online> online = con.connection().cache().characters
	.online(con.characterId());

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsBoolHolder isOnline = getOnline().test(onl -> onl.online);

	@Getter(lazy = true)
	private final ObsObjHolder<OffsetDateTime> lastlogin = getOnline().map(onl -> ESITools.convertDate(onl.last_login));

	@Getter(lazy = true)
	private final ObsObjHolder<OffsetDateTime> lastlogout = getOnline().map(onl -> ESITools.convertDate(onl.last_logout));

	// slots for industry jobs

	/**
	 *
	 * the max research slots this character has from its skills.
	 */
	@Getter(lazy = true)
	private final ObsIntHolder maxResearchSlots = makeMaxResearchSlots();

	protected ObsIntHolder makeMaxResearchSlots() {
		ObsMapHolder<Integer, Integer> cskills = skills.ID2Level();
		return cskills.at(3406, 0).mapInt(i -> i).add(cskills.at(24624, 0).mapInt(i -> i)).add(1);
	}

	/**
	 *
	 * the amount of research slots this character has available
	 */
	@Getter(lazy = true)
	private final ObsIntHolder availResSlots = makeAvailResSlots();

	protected ObsIntHolder makeAvailResSlots() {
		ObsIntHolder allSlots = getMaxResearchSlots();
		ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = industry.getResearchJobs();
		ObsIntHolder corpOwnedJobs = con.corporation.industry.getResearchJobs()
				.filter(j -> j.installer_id == con.characterId()).size();
		return allSlots.sub(jobs.size()).sub(corpOwnedJobs);
	}

	/**
	 *
	 * the max production slots this character has from its skills.
	 */
	@Getter(lazy = true)
	private final ObsIntHolder maxProdSlots = makeMaxProdSlots();

	protected ObsIntHolder makeMaxProdSlots() {
		ObsMapHolder<Integer, Integer> cskills = skills.ID2Level();
		return cskills.at(3387, 0).mapInt(i -> i).add(cskills.at(24625, 0).mapInt(i -> i)).add(1);
	}

	/**
	 *
	 * the amount of production slots this character has available
	 */
	@Getter(lazy = true)
	private final ObsIntHolder availProdSlots = makeAvailProdSlots();

	protected ObsIntHolder makeAvailProdSlots() {
		ObsIntHolder allSlots = getMaxProdSlots();
		ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = industry.getProductionJobs();
		ObsIntHolder corpOwnedJobs = con.corporation.industry.getProductionJobs()
				.filter(j -> j.installer_id == con.characterId()).size();
		return allSlots.sub(jobs.size()).sub(corpOwnedJobs);
	}

	/**
	 *
	 * the max reaction slots this character has from its skills.
	 */
	@Getter(lazy = true)
	private final ObsIntHolder maxReactionSlots = makeMaxReactionSlots();

	protected ObsIntHolder makeMaxReactionSlots() {
		ObsMapHolder<Integer, Integer> cskills = skills.ID2Level();
		return cskills.at(45749, 0).mapInt(i -> i).add(cskills.at(45748, 0).mapInt(i -> i)).add(1);
	}

	/**
	 *
	 * the amount of reaction slots this character has available
	 */
	@Getter(lazy = true)
	private final ObsIntHolder availReactionSlots = makeAvailReactionSlots();

	/**
	 *
	 * @return the amount of reaction slots this character has available
	 */
	protected ObsIntHolder makeAvailReactionSlots() {
		ObsIntHolder allSlots = getMaxReactionSlots();
		ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = industry.getReactionJobs();
		ObsIntHolder corpOwnedJobs = con.corporation.industry.getReactionJobs()
				.filter(j -> j.installer_id == con.characterId()).size();
		return allSlots.sub(jobs.size()).sub(corpOwnedJobs);
	}

	//
	// assets
	//

	public final Assets assets;

	/**
	 * get the assets and production of a character
	 *
	 * @return the map of itemid to qtty for each assets this character owns.
	 */
	public Map<Integer, Long> getAssetsProd() {
		Map<Integer, Long> massets = assets.getAvailable().get().values().stream().flatMap(m -> m.entrySet().stream())
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), Long::sum));
		Map<Integer, Long> prod = industry.getJobs().get().stream().parallel().filter(Industry::isManufacture)
				.collect(Collectors.toMap(e -> e.product_type_id, e -> (long) e.runs, Long::sum));
		return Stream.concat(massets.entrySet().stream(), prod.entrySet().stream())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, Long::sum));
	}

	//
	// journal
	//

	@Getter(lazy = true)
	private final ObsMapHolder<Long, M_get_journal_13> journal = con.connection().cache().characters
	.wallet_journal(con.characterId()).toMap(entry -> entry.id);

	//
	// standings
	//

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, M_get_standings_3> standings = con.connection().cache().characters
	.standings(con.characterId()).toMap(std -> std.from_id);

	private Map<Integer, ObsObjHolder<Float>> cacheStanding = new HashMap<>();

	public ObsObjHolder<Float> getStanding(int id) {
		ObsObjHolder<Float> ret = cacheStanding.get(id);
		if (ret == null) {
			ObsMapHolder<Integer, M_get_standings_3> std = getStandings();
			synchronized (cacheStanding) {
				ret = cacheStanding.get(id);
				if (ret == null) {
					ret = std.at(id, new M_get_standings_3()).map(m -> m.standing);
					cacheStanding.put(id, ret);
				}
			}
		}
		return ret;
	}

	//
	// LP
	//

	@Getter(lazy = true)
	private final ObsMapHolder<Integer, R_get_characters_character_id_loyalty_points> LPs = con.connection()
	.cache().characters.loyalty_points(con.characterId()).toMap(lp -> lp.corporation_id);

	//
	// skills and training
	//

	/**
	 * current SP acquisition based on skill being trained and attributes.
	 */
	@Getter(lazy = true)
	private final ObsDoubleHolder currentHourlySPRate = makeCurrentHourlySPRate();

	protected ObsDoubleHolder makeCurrentHourlySPRate() {
		ObsDoubleHolderImpl ret = new ObsDoubleHolderImpl();
		R_get_universe_types_type_id[] holdSkil = new R_get_universe_types_type_id[1];
		R_get_characters_character_id_attributes[] holdAtt = new R_get_characters_character_id_attributes[1];
		Runnable apply = () -> {
			if (holdSkil[0] != null && holdAtt[0] != null) {
				ret.set(getHourlySPRate(holdSkil[0], holdAtt[0]));
			}
		};
		skills.getTrainingSkill().follow((newValue) -> {
			holdSkil[0] = newValue;
			apply.run();
		});
		attributes.values().follow((newValue) -> {
			holdAtt[0] = newValue;
			apply.run();
		});
		return ret;
	}

	public static double getHourlySPRate(R_get_universe_types_type_id skill,
			R_get_characters_character_id_attributes attributes) {
		if (skill.type_id == 0) {
			return 0.0;
		}
		get_dogma_dynamic_items_type_id_item_id_dogma_attributes dogma_primary = Stream.of(skill.dogma_attributes)
				.filter(att -> att.attribute_id == 180).findAny().orElse(null);
		get_dogma_dynamic_items_type_id_item_id_dogma_attributes dogma_secondary = Stream.of(skill.dogma_attributes)
				.filter(att -> att.attribute_id == 181).findAny().orElse(null);
		return 60 * (dogma_primary != null ? Attributes.getAttribute((int) dogma_primary.value, attributes) : 0)
				+ 30 * (dogma_secondary != null ? Attributes.getAttribute((int) dogma_secondary.value, attributes) : 0);
	}

	//
	// alpha state
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObsBoolHolder isAlpha = makeIsAlpha();

	protected ObsBoolHolder makeIsAlpha() {
		ObsBoolHolder limited = skills.hasLimitedskill();
		ObsBoolHolder training = skills.getCurrentSkillAvgAcquisitionRate().test(d -> d > 0);
		ObsDoubleHolder theoretical = getCurrentHourlySPRate();
		ObsDoubleHolder effective = skills.getCurrentSkillAvgAcquisitionRate();
		return limited.or(training.and(effective.div(theoretical).test(d -> d < 0.55)));
	}

}
