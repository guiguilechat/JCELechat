package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Bookmarks;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Informations;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.LocationCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_assets_8;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_blueprints_8;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_online;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsMapHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;

public class EveCharacter {

	protected final ESIAccount con;

	public EveCharacter(ESIAccount con) {
		this.con = con;
		infos = new Informations(con);
		bms = new Bookmarks(con);
	}

	public final Informations infos;

	public final Bookmarks bms;

	//
	// roles
	//

	private ObservableSet<String> rolesCache = null;
	private ObservableSet<String> rolesHQCache = null;
	private ObservableSet<String> rolesBaseCache = null;
	private ObservableSet<String> rolesOtherCache = null;

	private ObsObjHolder<R_get_characters_character_id_roles> rolesobs;

	private void makeRoleRetrieve() {
		if (rolesobs != null) {
			return;
		}
		synchronized (this) {
			if (rolesobs == null) {
				rolesobs = con.raw.cache.characters.roles(con.characterId());
				rolesCache = FXCollections.observableSet();
				rolesHQCache = FXCollections.observableSet();
				rolesBaseCache = FXCollections.observableSet();
				rolesOtherCache = FXCollections.observableSet();
				rolesobs.follow((o, old, now) -> handleNewRoles(now));
			}
		}
	}

	public void handleNewRoles(R_get_characters_character_id_roles newroles) {
		if (newroles == null) {
			return;
		}
		HashSet<String> roles = new HashSet<>(Arrays.asList(newroles.roles));
		rolesCache.retainAll(roles);
		rolesCache.addAll(roles);
		HashSet<String> rolesHQ = new HashSet<>(Arrays.asList(newroles.roles_at_hq));
		rolesHQCache.retainAll(rolesHQ);
		rolesHQCache.addAll(rolesHQ);
		HashSet<String> rolesBase = new HashSet<>(Arrays.asList(newroles.roles_at_base));
		rolesBaseCache.retainAll(rolesBase);
		rolesBaseCache.addAll(rolesBase);
		HashSet<String> rolesOther = new HashSet<>(Arrays.asList(newroles.roles_at_other));
		rolesOtherCache.retainAll(rolesOther);
		rolesOtherCache.addAll(rolesOther);
	}

	public ObservableSet<String> getRoles() {
		makeRoleRetrieve();
		rolesobs.waitData();
		return rolesCache;
	}

	public ObservableSet<String> getRolesHQ() {
		makeRoleRetrieve();
		rolesobs.waitData();
		return rolesHQCache;
	}

	public ObservableSet<String> getRolesBase() {
		makeRoleRetrieve();
		rolesobs.waitData();
		return rolesBaseCache;
	}

	public ObservableSet<String> getRolesOther() {
		makeRoleRetrieve();
		rolesobs.waitData();
		return rolesOtherCache;
	}

	//
	// industry jobs
	//

	/**
	 * fetch the list of industry jobs for this character. If the cache delay is
	 * not expired, uses the cached version.
	 *
	 * @return the internal cache of the jobs for this character. successive calls
	 *         will return the same value.
	 *
	 */
	public ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> getIndustryJobs() {
		return con.raw.cache.characters.industry_jobs(con.characterId(), false);
	}

	public static boolean isManufacture(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 1;
	}

	public static boolean isTE(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 3;
	}

	public static boolean isME(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 4;
	}

	public static boolean isCopy(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 5;
	}

	public static boolean isInvention(R_get_characters_character_id_industry_jobs job) {
		return job.activity_id == 8;
	}

	public ObservableNumberValue availableResearchSlots() {
		ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> jobs = getIndustryJobs();
		LongBinding charJobsVar = Bindings.createLongBinding(() -> {
			synchronized (jobs) {
				return jobs.copy().values().stream().filter(j -> isCopy(j) || isInvention(j) || isME(j) || isTE(j)).count();
			}
		}, jobs.asObservable());
		ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> corpJobs = con.corporation.getIndustryJobs();
		LongBinding corpJobsVar = Bindings.createLongBinding(() -> {
			synchronized (corpJobs) {
				return corpJobs.copy().values().stream().filter(j -> j.installer_id == con.characterId())
						.filter(
								j -> Corporation.isCopy(j) || Corporation.isInvetion(j) || Corporation.isME(j) || Corporation.isTE(j))
						.count();
			}
		}, corpJobs.asObservable());
		return new SimpleIntegerProperty(1 + getSkills().getOrDefault(3406, 0) + getSkills().getOrDefault(24624, 0))
				.subtract(charJobsVar).subtract(corpJobsVar);
	}

	public ObservableNumberValue availableManufSlots() {
		ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> charjobs = getIndustryJobs();
		LongBinding charJobsVar = Bindings.createLongBinding(() -> {
			synchronized (charjobs) {
				return charjobs.copy().values().stream().filter(j -> isManufacture(j)).count();
			}
		}, charjobs.asObservable());
		ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> corpJobs = con.corporation.getIndustryJobs();
		LongBinding corpJobsVar = Bindings.createLongBinding(() -> {
			synchronized (corpJobs) {
				return corpJobs.copy().values().stream().filter(j -> j.installer_id == con.characterId())
						.filter(j -> Corporation.isManufacture(j)).count();
			}
		}, corpJobs.asObservable());
		return new SimpleIntegerProperty(1 + getSkills().getOrDefault(3387, 0) + getSkills().getOrDefault(24625, 0))
				.subtract(charJobsVar).subtract(corpJobsVar);
	}

	//
	// blueprints
	//


	public ObsMapHolder<Long, M_get_blueprints_8> getBlueprints() {
		return con.raw.cache.characters.blueprints(con.characterId());
	}

	//
	// online state and position
	//

	protected ObsObjHolder<R_get_characters_character_id_online> getOnline() {
		return con.raw.cache.characters.online(con.characterId());
	}

	public boolean isOnline() {
		return getOnline().get().online;
	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	static {
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	public Date lastLogin() {
		try {
			synchronized (sdf) {
				return sdf.parse(getOnline().get().last_login);
			}
		} catch (ParseException ex) {
			throw new UnsupportedOperationException("catch this", ex);
		}
	}

	public Date lastLogout() {
		try {
			synchronized (sdf) {
				return sdf.parse(getOnline().get().last_logout);
			}
		} catch (ParseException ex) {
			throw new UnsupportedOperationException("catch this", ex);
		}
	}

	private LocationCache location = null;

	public LocationCache getLocation() {
		if (location == null) {
			synchronized (this) {
				if (location == null) {
					location = new LocationCache(con);
					try {
						location.waitData();
					} catch (InterruptedException e) {
						throw new UnsupportedOperationException("catch this", e);
					}
				}
			}
		}
		return location;
	}

	//
	// assets
	//

	// locationid->typeid->number
	private ObsMapHolder<Long, ObservableMap<Integer, Integer>> cachedAssets = null;

	/**
	 *
	 * @return the locationID->typeid->quantity
	 */
	public ObsMapHolder<Long, ObservableMap<Integer, Integer>> getAssets() {
		if (cachedAssets == null) {
			synchronized (this) {
				if (cachedAssets==null) {
					ObsListHolder<M_get_assets_8> assets = con.raw.cache.characters.assets(con.characterId());
					ObservableMap<Long, ObservableMap<Integer, Integer>> map = FXCollections.observableHashMap();
					cachedAssets = con.raw.cache.toHolder(map);
					assets.follow(c -> applyNewAssets(c, map));
				}
			}
		}
		return cachedAssets;
	}

	/**
	 * called when a change happens to the list of assets. When this happens, we
	 * recreate the whole map and put it back .
	 *
	 * @param c
	 * @param map
	 */
	protected void applyNewAssets(Change<? extends M_get_assets_8> c,
			ObservableMap<Long, ObservableMap<Integer, Integer>> map) {

		// the listener is called everytime the full list of items in
		// modified. thus everytime, we recreate it
		M_get_assets_8[] itemsArr = c.getAddedSubList().stream().filter(asset -> !"AutoFit".equals(asset.location_flag))
				.toArray(M_get_assets_8[]::new);

		// we make the map of itemid->locations. if a location is actually an
		// asset, we iteratively map it to this asset's location instead
		Map<Long, Long> baseLocationMap = Stream.of(itemsArr).collect(Collectors.toMap(i -> i.item_id, i -> i.location_id));
		Map<Long, Long> idToLocation = baseLocationMap.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> {
			Long ret = e.getValue();
			while (baseLocationMap.containsKey(ret)) {
				ret = baseLocationMap.get(ret);
			}
			return ret;
		}));
		Map<Long, Map<Integer, Integer>> newitems = Stream.of(itemsArr)
				.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), EveCharacter::makeMap, EveCharacter::mergeMap));
		map.keySet().retainAll(newitems.keySet());
		for (Entry<Long, Map<Integer, Integer>> e : newitems.entrySet()) {
			ObservableMap<Integer, Integer> om = map.get(e.getKey());
			if (om == null) {
				om = FXCollections.observableHashMap();
				map.put(e.getKey(), om);
			}
			om.keySet().retainAll(e.getValue().keySet());
			om.putAll(e.getValue());
		}

	}

	private static Map<Integer, Integer> makeMap(M_get_assets_8 asset) {
		Map<Integer, Integer> ret = new HashMap<>();
		ret.put(asset.type_id, asset.quantity);
		return ret;
	}

	private static Map<Integer, Integer> mergeMap(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
		for (Entry<Integer, Integer> e : m2.entrySet()) {
			m1.merge(e.getKey(), e.getValue(), (a, b) -> a + b);
		}
		return m1;
	}

	//
	// skills
	//

	private Map<Integer, Integer> skills = null;

	public synchronized Map<Integer, Integer> getSkills() {
		if (skills == null) {
			skills = Stream.of(con.raw.get_characters_skills(con.characterId(), null).skills)
					.collect(Collectors.toMap(s -> s.skill_id, s -> s.active_skill_level));
		}
		return skills;
	}

	//

	public ObsMapHolder<Long, R_get_characters_character_id_orders> getMarketOrders() {
		return con.raw.cache.characters.orders(con.characterId());
	}

}