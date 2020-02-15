package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.AutoFit;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.CorpSAG1;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.CorpSAG2;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.CorpSAG3;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.CorpSAG4;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.CorpSAG5;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.CorpSAG6;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.CorpSAG7;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.Deliveries;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.Hangar;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.HangarAll;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.Locked;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.ShipHangar;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.Unlocked;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.CorpAssetFolder;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.CorpBookmarks;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Market;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.SystemAsset;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.Wallet;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_ICOAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_divisions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_membertracking;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_roles_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_structures;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_titles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_journal;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Corporation {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Corporation.class);

	public final ESIAccount con;

	public final CorpBookmarks bms;
	public final Wallet wallet;
	public final Market market;

	public Corporation(ESIAccount con) {
		this.con = con;
		bms = new CorpBookmarks(con);
		wallet = new Wallet(this);
		market = new Market(this);
	}

	public int getId() {
		int ret = con.character.infos.corporationId().get();
		return ret;
	}

	public ObsObjHolder<R_get_corporations_corporation_id> getInformations() {
		return ESIStatic.INSTANCE.cache.corporations.get(getId());
	}

	public String getName() {
		return getInformations().get().name;
	}

	public ObsObjHolder<R_get_corporations_corporation_id_divisions> getDivisions() {
		return con.raw.cache.corporations.divisions(getId());
	}

	//
	// industry jobs
	//

	private ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> cachedIndustryJobs = null;

	public ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> getIndustryJobs() {
		if (cachedIndustryJobs == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedIndustryJobs == null) {
					cachedIndustryJobs = con.raw.cache.corporations.industry_jobs(getId(), false).toMap(j -> j.job_id);
				}
			});
		}
		return cachedIndustryJobs;
	}

	private ObsSetHolder<Long> cachedUsedBPs = null;

	public ObsSetHolder<Long> getUsedBPs() {
		if (cachedUsedBPs == null) {
			ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> jobs = getIndustryJobs();
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedUsedBPs == null) {
					cachedUsedBPs = jobs.values().mapItems(j -> j.blueprint_id).distinct();
				}
			});
		}
		return cachedUsedBPs;
	}

	public static boolean isManufacture(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 1;
	}

	public static boolean isTE(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 3;
	}

	public static boolean isME(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 4;
	}

	public static boolean isCopy(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 5;
	}

	public static boolean isInvention(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 8;
	}

	//
	// assets
	//

	public ObsListHolder<R_get_corporations_corporation_id_assets> getAssetsList() {
		// caching is already present at the cache level.
		return con.raw.cache.corporations.assets(getId());
	}

	public Map<String, SystemAsset> getAssetTree() {
		List<R_get_corporations_corporation_id_assets> assets = getAssetsList().get();
		Map<String, SystemAsset> ret = new HashMap<>();
		Map<Long, CorpAssetFolder> idToFolder = assets.stream()
				.collect(Collectors.toMap(asset -> asset.item_id, asset -> new CorpAssetFolder(asset)));
		for (CorpAssetFolder folder : idToFolder.values()) {
			CorpAssetFolder parent = idToFolder.get(folder.asset.location_id);
			if (parent != null) {
				parent.addAsset(folder);
			} else {
				Location resolved = Location.resolve(con, folder.asset.location_id);
				SystemAsset sysasset = null;
				switch (resolved.type) {
				case SYSTEM:
					// the location_id is a system :
					// this is our structure
					sysasset = ret.computeIfAbsent(resolved.name,k-> new SystemAsset());
					sysasset.owned.put(Location.resolve(con, folder.asset.item_id).name, folder);
					break;
				case STRUCTURE:
					// location_id is a structure, that we don't own
					if (resolved.ref == null) {
						// we can't resolved the structure. it's in system "unresolved"
						sysasset =ret.computeIfAbsent("unresolved",k-> new SystemAsset());
					} else {
						// structure is resolved. get its system.
						String name = Location.resolve(con, ((R_get_universe_structures_structure_id) resolved.ref).solar_system_id).name;
						sysasset = ret.computeIfAbsent(name, k -> new SystemAsset());
					}
					sysasset.rent.put(resolved.name, folder);
					break;
				case STATION:
					// this is a hangar we rent in a station
					R_get_universe_stations_station_id station = (R_get_universe_stations_station_id) resolved.ref;
					String name = Location.resolve(con, station.system_id).name;
					sysasset = ret.computeIfAbsent(name, k -> new SystemAsset());
					sysasset.rent.put(resolved.name, folder);
					break;
				default:
					throw new UnsupportedOperationException("case not handled " + resolved.type);
				}
			}
		}
		return ret;
	}
	/**
	 *
	 * @return the location->typeid->quantity
	 */
	public ObservableMap<Long, ObservableMap<Integer, Long>> getAssets() {
		ObservableMap<Long, ObservableMap<Integer, Long>> assets = FXCollections.observableMap(new LinkedHashMap<>());
		R_get_corporations_corporation_id_assets[] itemsArr = con.raw.cache.corporations.assets(getId()).get().stream()
				.filter(asset -> !get_corporations_corporation_id_assets_location_flag.AutoFit.equals(asset.location_flag))
				.toArray(R_get_corporations_corporation_id_assets[]::new);
		try {
			// we make the map of itemid->locations. if a location is actually an
			// asset, we iterally map it to this asset's location instead
			Map<Long, Long> baseLocationMap = Stream.of(itemsArr)
					.collect(Collectors.toMap(i -> i.item_id, i -> i.location_id, (a, b) -> {
						var sortedItemsArray = Arrays.copyOf(itemsArr, itemsArr.length);
						Arrays.sort(sortedItemsArray, (o1, o2) -> (int) Math.signum(o1.item_id - o2.item_id));
						logger.warn("double item location when fetching corporation assets " + a + ";" + b);
						for (R_get_corporations_corporation_id_assets asset : sortedItemsArray) {
							logger.warn("" + asset.item_id + " : loc=" + asset.location_id + " type=" + asset.type_id + " x"
									+ asset.quantity);
						}
						return b;
					}));
			Map<Long, Long> idToLocation = baseLocationMap.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> {
				Long ret = e.getValue();
				while (baseLocationMap.containsKey(ret)) {
					ret = baseLocationMap.get(ret);
				}
				return ret;
			}));
			Map<Long, Map<Integer, Long>> newitems = Stream.of(
					itemsArr)
					.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), Corporation::makeMap, Corporation::mergeMap));

			for (Entry<Long, Map<Integer, Long>> e : newitems.entrySet()) {
				ObservableMap<Integer, Long> om = assets.get(e.getKey());
				if (om == null) {
					om = FXCollections.observableHashMap();
					assets.put(e.getKey(), om);
				}
				om.keySet().retainAll(e.getValue().keySet());
				om.putAll(e.getValue());
			}
		} catch (Exception e) {
			logger.warn("while getting assets", e);
		}
		return assets;
	}

	private ObsMapHolder<Long, Map<Integer, Long>> availableAssets = null;

	public ObsMapHolder<Long, Map<Integer, Long>> getAvailableAssets() {
		if (availableAssets == null) {
			ObsListHolder<R_get_corporations_corporation_id_assets> assetList = getAssetsList();
			synchronized (assetList) {
				if (availableAssets == null) {
					ObservableMap<Long, Map<Integer, Long>> internal = FXCollections.observableMap(new LinkedHashMap<>());
					ObsMapHolderImpl<Long, Map<Integer, Long>> ret = new ObsMapHolderImpl<>(
							internal);
					assetList.follow(l -> {
						Map<Long, Map<Integer, Long>> newmap = availableAssetsByLocation(l);
						logger.debug("corporation " + getName() + " has available assets " + newmap);

						internal.keySet().retainAll(newmap.keySet());
						internal.putAll(newmap);
						ret.dataReceived();
					});
					availableAssets = ret;
				}
			}
		}
		return availableAssets;
	}

	private static final HashSet<get_corporations_corporation_id_assets_location_flag> availableAssetsFlags = new HashSet<>(
			Arrays.asList(AutoFit, CorpSAG1, CorpSAG2, CorpSAG3, CorpSAG4, CorpSAG5, CorpSAG6, CorpSAG7, Deliveries, Hangar,
					HangarAll, Locked, ShipHangar, Unlocked));

	/**
	 * filter and group the assets from an asset lists
	 *
	 * @param assets
	 *          the list of assets
	 * @return the map locationid -> typeid -> qtty
	 */
	public static Map<Long, Map<Integer, Long>> availableAssetsByLocation(
			Iterable<R_get_corporations_corporation_id_assets> assets) {
		// remove all the items that have a bad location_flag
		R_get_corporations_corporation_id_assets[] itemsArr = StreamSupport.stream(assets.spliterator(), false)
				.toArray(R_get_corporations_corporation_id_assets[]::new);

		// we make the map of itemid->locations. if a location is actually an
		// asset, we iteratively map it to this asset's location instead
		Map<Long, Long> baseLocationMap = Stream.of(itemsArr)
				.collect(Collectors.toMap(i -> i.item_id, i -> i.location_id, (l1, l2) -> l1));
		Map<Long, Long> idToLocation = baseLocationMap.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> {
			Long ret = e.getValue();
			while (baseLocationMap.containsKey(ret)) {
				ret = baseLocationMap.get(ret);
			}
			return ret;
		}));
		Map<Long, Map<Integer, Long>> ret = Stream.of(
				itemsArr)
				.filter(asset -> !asset.is_singleton && availableAssetsFlags.contains(
						asset.location_flag))
				.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), Corporation::makeMap, Corporation::mergeMap));
		return ret;
	}

	private static Map<Integer, Long> makeMap(R_get_corporations_corporation_id_assets asset) {
		Map<Integer, Long> ret = new HashMap<>();
		ret.put(asset.type_id, (long) asset.quantity);
		return ret;
	}

	private static Map<Integer, Long> mergeMap(Map<Integer, Long> m1, Map<Integer, Long> m2) {
		for (Entry<Integer, Long> e : m2.entrySet()) {
			m1.merge(e.getKey(), e.getValue(), (a, b) -> a + b);
		}
		return m1;
	}

	public ObsMapHolder<Long, R_get_corporations_corporation_id_blueprints> getBlueprints() {
		return ObsMapHolderImpl.toMap(con.raw.cache.corporations.blueprints(getId()), bp -> bp.item_id);
	}

	//
	// structures and facilities
	//

	private ObsMapHolder<Long, R_get_corporations_corporation_id_structures> cachedStructures = null;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_structures> getStructures() {
		if (cachedStructures == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedStructures == null) {
					cachedStructures = con.raw.cache.corporations.structures(getId()).toMap(str -> str.structure_id);
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
					cachedFacilities = con.raw.cache.corporations.facilities(getId()).toMap(str -> str.facility_id);
				}
			});
		}
		return cachedFacilities;
	}

	//
	// journal
	//

	private Map<Integer, ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_journal>> cachedJournals = new HashMap<>();

	public ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_journal> getJournal(int division_id) {
		if (cachedJournals.get(division_id) == null) {
			LockWatchDog.BARKER.syncExecute(cachedJournals, () -> {
				if (cachedJournals.get(division_id) == null) {
					cachedJournals.put(division_id,
							con.raw.cache.corporations.wallets_journal(getId(), division_id).toMap(j -> j.id));
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
					cachedStandings = con.raw.cache.corporations.standings(getId()).toMap(std -> std.from_id);
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
					cachedContacts = con.raw.cache.corporations.contacts(getId()).toMap(contact -> contact.contact_id);
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
					cachedContacts_labels = con.raw.cache.corporations.contacts_labels(getId()).toMap(l -> l.label_id,
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
		return con.raw.cache.corporations.members(getId());
	}

	public ObsObjHolder<Integer> getMembersLimit() {
		return con.raw.cache.corporations.members_limit(getId());
	}

	private ObsMapHolder<Integer, int[]> cachedMembersTitles = null;

	public ObsMapHolder<Integer, int[]> getMemberTitles() {
		if (cachedMembersTitles == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedMembersTitles == null) {
					cachedMembersTitles = con.raw.cache.corporations.members_titles(getId()).toMap(title -> title.character_id,
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
					cachedTitles = con.raw.cache.corporations.titles(getId()).toMap(title -> title.title_id);
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
					cachedMemberstracking = con.raw.cache.corporations.membertracking(getId()).toMap(track -> track.character_id);
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
					cachedRoles = con.raw.cache.corporations.roles(getId()).toMap(roles -> roles.character_id);
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
		return con.raw.cache.corporations.roles_history(getId());
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
				}});
		}
		return cachedMonthWars;
	}

}
