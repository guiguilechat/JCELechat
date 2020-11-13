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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_loyalty_points;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_online;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_journal;
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
import fr.lelouet.tools.synchronization.LockWatchDog;

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
		return infos.name().get();
	}

	//
	// roles
	//

	public ObsObjHolder<R_get_characters_character_id_roles> getRolesData() {
		return con.raw.cache.characters.roles(con.characterId());
	}

	private ObsSetHolder<get_characters_character_id_roles_roles> rolesCache = null;

	public ObsSetHolder<get_characters_character_id_roles_roles> getRoles() {
		if (rolesCache == null) {
			ObsObjHolder<R_get_characters_character_id_roles> data = getRolesData();
			LockWatchDog.BARKER.syncExecute(data, () -> {
				if (rolesCache == null) {
					rolesCache = data.toList(roles -> Arrays.asList(roles.roles)).distinct();
				}
			});
		}
		return rolesCache;
	}

	private ObsSetHolder<get_characters_character_id_roles_roles_at_hq> rolesHQCache = null;

	public ObsSetHolder<get_characters_character_id_roles_roles_at_hq> getRolesHQ() {
		if (rolesHQCache == null) {
			ObsObjHolder<R_get_characters_character_id_roles> data = getRolesData();
			LockWatchDog.BARKER.syncExecute(data, () -> {
				if (rolesHQCache == null) {
					rolesHQCache = data.toList(roles -> Arrays.asList(roles.roles_at_hq)).distinct();
				}
			});
		}
		return rolesHQCache;
	}

	private ObsSetHolder<get_characters_character_id_roles_roles_at_base> rolesBaseCache = null;

	public ObsSetHolder<get_characters_character_id_roles_roles_at_base> getRolesBase() {
		if (rolesBaseCache == null) {
			ObsObjHolder<R_get_characters_character_id_roles> data = getRolesData();
			LockWatchDog.BARKER.syncExecute(data, () -> {
				if (rolesBaseCache == null) {
					rolesBaseCache = data.toList(roles -> Arrays.asList(roles.roles_at_base)).distinct();
				}
			});
		}
		return rolesBaseCache;
	}

	private ObsSetHolder<get_characters_character_id_roles_roles_at_other> rolesOtherCache = null;

	public ObsSetHolder<get_characters_character_id_roles_roles_at_other> getRolesOthers() {
		if (rolesOtherCache == null) {
			ObsObjHolder<R_get_characters_character_id_roles> data = getRolesData();
			LockWatchDog.BARKER.syncExecute(data, () -> {
				if (rolesOtherCache == null) {
					rolesOtherCache = data.toList(roles -> Arrays.asList(roles.roles_at_other)).distinct();
				}
			});
		}
		return rolesOtherCache;
	}

	//
	// online state
	//

	private ObsObjHolder<R_get_characters_character_id_online> online = null;

	protected ObsObjHolder<R_get_characters_character_id_online> getOnline() {
		if (online == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (online == null) {
					online = con.raw.cache.characters.online(con.characterId());
				}
			});
		}
		return online;
	}

	private ObsBoolHolder isonline = null;

	public ObsBoolHolder isOnline() {
		if (isonline == null) {
			LockWatchDog.BARKER.syncExecute(getOnline(), () -> {
				if (isonline == null) {
					isonline = getOnline().test(onl -> onl.online);
				}
			});
		}
		return isonline;
	}

	private ObsObjHolder<OffsetDateTime> lastlogin = null;

	public ObsObjHolder<OffsetDateTime> getLastLogin() {
		if (lastlogin == null) {
			LockWatchDog.BARKER.syncExecute(getOnline(), () -> {
				if (lastlogin == null) {
					lastlogin = getOnline().map(onl -> ESITools.convertDate(onl.last_login));
				}
			});
		}
		return lastlogin;
	}

	private ObsObjHolder<OffsetDateTime> lastlogout = null;

	public ObsObjHolder<OffsetDateTime> getLastlogout() {
		if (lastlogout == null) {
			LockWatchDog.BARKER.syncExecute(getOnline(), () -> {
				if (lastlogout == null) {
					lastlogout = getOnline().map(onl -> ESITools.convertDate(onl.last_logout));
				}
			});
		}
		return lastlogout;
	}

	// slots for industry jobs

	private ObsIntHolder cacheMaxResearchSlots = null;

	/**
	 *
	 * @return the max research slots this character has from its skills.
	 */
	public ObsIntHolder getMaxResearchSlots() {
		if (cacheMaxResearchSlots == null) {
			ObsMapHolder<Integer, Integer> cskills = skills.ID2Level();
			synchronized (cskills) {
				if (cacheMaxResearchSlots == null) {
					cacheMaxResearchSlots = cskills.at(3406, 0).mapInt(i -> i).add(cskills.at(24624, 0).mapInt(i -> i)).add(1);
				}
			}
		}
		return cacheMaxResearchSlots;
	}

	private ObsIntHolder cacheAvailResSlots;

	/**
	 *
	 * @return the amount of research slots this character has available
	 */
	public ObsIntHolder getAvailResSlots() {
		if (cacheAvailResSlots == null) {
			ObsIntHolder allSlots = getMaxResearchSlots();
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = industry.getResearchJobs();
			synchronized (jobs) {
				if (cacheAvailResSlots == null) {
					ObsIntHolder corpOwnedJobs = con.corporation.industry.getResearchJobs()
							.filter(j -> j.installer_id == con.characterId()).size();
					cacheAvailResSlots = allSlots.sub(jobs.size()).sub(corpOwnedJobs);
				}
			}
		}
		return cacheAvailResSlots;
	}

	private ObsIntHolder cacheMaxProdSlots = null;

	/**
	 *
	 * @return the max production slots this character has from its skills.
	 */
	public ObsIntHolder getMaxProdSlots() {
		if (cacheMaxProdSlots == null) {
			ObsMapHolder<Integer, Integer> cskills = skills.ID2Level();
			synchronized (cskills) {
				if (cacheMaxProdSlots == null) {
					cacheMaxProdSlots = cskills.at(3387, 0).mapInt(i -> i).add(cskills.at(24625, 0).mapInt(i -> i)).add(1);
				}
			}
		}
		return cacheMaxProdSlots;
	}

	private ObsIntHolder cacheAvailProdSlots;

	/**
	 *
	 * @return the amount of production slots this character has available
	 */
	public ObsIntHolder getAvailProdSlots() {
		if (cacheAvailProdSlots == null) {
			ObsIntHolder allSlots = getMaxProdSlots();
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = industry.getProductionJobs();
			synchronized (jobs) {
				if (cacheAvailProdSlots == null) {
					ObsIntHolder corpOwnedJobs = con.corporation.industry.getProductionJobs()
							.filter(j -> j.installer_id == con.characterId()).size();
					cacheAvailProdSlots = allSlots.sub(jobs.size()).sub(corpOwnedJobs);
				}
			}
		}
		return cacheAvailProdSlots;
	}

	private ObsIntHolder cacheMaxReactionSlots = null;

	/**
	 *
	 * @return the max reaction slots this character has from its skills.
	 */
	public ObsIntHolder getMaxReactionSlots() {
		if (cacheMaxReactionSlots == null) {
			ObsMapHolder<Integer, Integer> cskills = skills.ID2Level();
			synchronized (cskills) {
				if (cacheMaxReactionSlots == null) {
					cacheMaxReactionSlots = cskills.at(45749, 0).mapInt(i -> i).add(cskills.at(45748, 0).mapInt(i -> i)).add(1);
				}
			}
		}
		return cacheMaxReactionSlots;
	}

	private ObsIntHolder cacheAvailReactionSlots;

	/**
	 *
	 * @return the amount of reaction slots this character has available
	 */
	public ObsIntHolder getAvailReactionSlots() {
		if (cacheAvailReactionSlots == null) {
			ObsIntHolder allSlots = getMaxReactionSlots();
			ObsListHolder<R_get_characters_character_id_industry_jobs> jobs = industry.getReactionJobs();
			synchronized (jobs) {
				if (cacheAvailReactionSlots == null) {
					ObsIntHolder corpOwnedJobs = con.corporation.industry.getReactionJobs()
							.filter(j -> j.installer_id == con.characterId()).size();
					cacheAvailReactionSlots = allSlots.sub(jobs.size()).sub(corpOwnedJobs);
				}
			}
		}
		return cacheAvailReactionSlots;
	}

	//
	// assets
	//

	public final Assets assets;

	/**
	 * get the assets and production of a character
	 *
	 * @param account
	 *          the account of a character
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

	private ObsMapHolder<Long, R_get_characters_character_id_wallet_journal> journal;

	public ObsMapHolder<Long, R_get_characters_character_id_wallet_journal> getJournal() {
		if (journal == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (journal == null) {
					journal = con.raw.cache.characters.wallet_journal(con.characterId()).toMap(j -> j.id);
				}
			});
		}
		return journal;
	}

	//
	// standings
	//

	private ObsMapHolder<Integer, M_get_standings_3> standings;

	public ObsMapHolder<Integer, M_get_standings_3> getStandings() {
		if (standings == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (standings == null) {
					standings = con.raw.cache.characters.standings(con.characterId()).toMap(std -> std.from_id);
				}
			});
		}
		return standings;
	}

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

	private ObsMapHolder<Integer, R_get_characters_character_id_loyalty_points> lps;

	public ObsMapHolder<Integer, R_get_characters_character_id_loyalty_points> getLPs() {
		if (lps == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (lps == null) {
					lps = con.raw.cache.characters.loyalty_points(con.characterId()).toMap(lp -> lp.corporation_id);
				}
			});
		}
		return lps;
	}

	//
	// skills and training
	//

	ObsDoubleHolder currentAcquisitionRate = null;

	public ObsDoubleHolder getCurrentHourlySPRate() {
		if (currentAcquisitionRate == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (currentAcquisitionRate == null) {
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
					attributes.get().follow((newValue) -> {
						holdAtt[0] = newValue;
						apply.run();
					});
					currentAcquisitionRate = ret;
				}
			});
		}
		return currentAcquisitionRate;
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

	ObsBoolHolder isAlpha = null;

	public ObsBoolHolder isAlpha() {
		if (isAlpha == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				ObsBoolHolder limited = skills.hasLimitedskill();
				ObsBoolHolder training = skills.getCurrentSkillAvgAcquisitionRate().test(d -> d > 0);
				ObsDoubleHolder theoretical = getCurrentHourlySPRate();
				ObsDoubleHolder effective = skills.getCurrentSkillAvgAcquisitionRate();
				isAlpha = limited.or(training.and(effective.div(theoretical).test(d -> d < 0.55)));
			});
		}
		return isAlpha;
	}

}
