package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Attributes;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Bookmarks;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Industry;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Informations;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Location;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Skills;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_loyalty_points;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_online;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_journal;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsBoolHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsIntHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
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

	public final Informations infos;

	public final Bookmarks bms;

	public final Skills skills;

	public final Location location;

	public final Industry industry;

	public final Attributes attributes;

	public EveCharacter(ESIAccount con) {
		this.con = con;
		infos = new Informations(con);
		bms = new Bookmarks(con);
		skills = new Skills(con);
		location = new Location(con);
		industry = new Industry(con);
		attributes = new Attributes(con);
	}

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
		Set<String> roles = Arrays.asList(newroles.roles).stream().map(r -> r.toString).collect(Collectors.toSet());
		rolesCache.retainAll(roles);
		rolesCache.addAll(roles);
		Set<String> rolesHQ = Arrays.asList(newroles.roles_at_hq).stream().map(r -> r.toString).collect(Collectors.toSet());
		rolesHQCache.retainAll(rolesHQ);
		rolesHQCache.addAll(rolesHQ);
		Set<String> rolesBase = Arrays.asList(newroles.roles_at_base).stream().map(r -> r.toString)
				.collect(Collectors.toSet());
		rolesBaseCache.retainAll(rolesBase);
		rolesBaseCache.addAll(rolesBase);
		Set<String> rolesOther = Arrays.asList(newroles.roles_at_other).stream().map(r -> r.toString)
				.collect(Collectors.toSet());
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
					lastlogout = getOnline()
							.map(onl -> ESITools.convertDate(onl.last_logout));
				}
			});
		}
		return lastlogout;
	}

	// slots for industry jobs

	private ObsIntHolder researchSlots = null;

	public ObsIntHolder availableResearchSlots() {
		if(researchSlots==null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (researchSlots == null) {
					ObsIntHolder jobs = industry.getResearchJobs().size();
					ObsIntHolder corpJobs = con.corporation.getIndustryJobs().values()
							.filter(j -> j.installer_id == con.characterId() && (Corporation.isCopy(j) || Corporation.isInvention(j)
									|| Corporation.isME(j) || Corporation.isTE(j)))
							.size();
					ObsIntHolder skill1 = skills.ID2Level().at(3406, 0).mapInt(i -> i);
					ObsIntHolder skill2 = skills.ID2Level().at(24624, 0).mapInt(i -> i);
					ObsIntHolder totalSlots = skill1.add(skill2).add(1);
					ObsIntHolder totalJobs = jobs.add(corpJobs);
					researchSlots = totalSlots.sub(totalJobs);
				}
			});
		}
		return researchSlots;
	}

	public ObservableNumberValue availableManufSlots() {
		ObsMapHolder<Integer, R_get_characters_character_id_industry_jobs> charjobs = industry.getIndustryJobs();
		charjobs.values().filter(Industry::isManufacture).size();
		LongBinding charJobsVar = Bindings.createLongBinding(() -> {
			synchronized (charjobs) {
				return charjobs.get().values().stream().filter(Industry::isManufacture).count();
			}
		}, charjobs.asObservable());
		ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> corpJobs = con.corporation.getIndustryJobs();
		LongBinding corpJobsVar = Bindings.createLongBinding(() -> {
			synchronized (corpJobs) {
				return corpJobs.get().values().stream().filter(j -> j.installer_id == con.characterId())
						.filter(j -> Corporation.isManufacture(j)).count();
			}
		}, corpJobs.asObservable());
		return new SimpleIntegerProperty(
				1 + skills.ID2Level().getOrDefault(3387, 0) + skills.ID2Level().getOrDefault(24625, 0)).subtract(charJobsVar)
				.subtract(corpJobsVar);
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
				if (cachedAssets == null) {
					ObsListHolder<R_get_characters_character_id_assets> assets = con.raw.cache.characters
							.assets(con.characterId());
					ObservableMap<Long, ObservableMap<Integer, Integer>> map = FXCollections.observableHashMap();
					cachedAssets = con.raw.cache.toHolder(map);
					synchronized (assets) {
						assets.followItems(c -> applyNewAssets(c, map));
						assets.follow((obj, old, ass) -> cachedAssets.dataReceived());
					}

				}
			}
		}
		return cachedAssets;
	}

	/**
	 * get the assets and production of a character
	 *
	 * @param account
	 *          the account of a character
	 * @return the map of itemid to qtty for each assets this character owns.
	 */
	public Map<Integer, Integer> getAssetsProd() {
		Map<Integer, Integer> assets = getAssets().get().values().parallelStream().flatMap(m -> m.entrySet().stream())
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), Integer::sum));
		Map<Integer, Integer> prod = industry.getIndustryJobs().get().values().stream().parallel()
				.filter(Industry::isManufacture)
				.collect(Collectors.toMap(e -> e.product_type_id, e -> e.runs, Integer::sum));
		return Stream.concat(assets.entrySet().stream(), prod.entrySet().stream())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, Integer::sum));
	}

	/**
	 * called when a change happens to the list of assets. When this happens, we
	 * recreate the whole map and put it back .
	 *
	 * @param c
	 * @param map
	 */
	protected void applyNewAssets(Change<? extends R_get_characters_character_id_assets> c,
			ObservableMap<Long, ObservableMap<Integer, Integer>> map) {
		c.next();

		// the listener is called everytime the full list of items in
		// modified. thus everytime, we recreate it
		R_get_characters_character_id_assets[] itemsArr = c.getAddedSubList().stream()
				.filter(asset -> !get_characters_character_id_assets_location_flag.AutoFit.equals(asset.location_flag))
				.toArray(R_get_characters_character_id_assets[]::new);

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

		Map<Long, Map<Integer, Integer>> newitems = Stream.of(itemsArr)
				.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), EveCharacter::makeMap, EveCharacter::mergeMap));
		synchronized (map) {
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
	}

	private static Map<Integer, Integer> makeMap(R_get_characters_character_id_assets asset) {
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
	// market orders
	//

	private ObsMapHolder<Integer, Integer> marketSOs = null;

	private ObsMapHolder<Integer, Integer> marketBOs = null;

	private void makeBOSOs() {
		if (marketSOs == null || marketBOs == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (marketSOs == null || marketBOs == null) {
					ObservableMap<Integer, Integer> underlyingsos = FXCollections.observableMap(new HashMap<>());
					ObsMapHolderImpl<Integer, Integer> newmarketSOs = new ObsMapHolderImpl<>(underlyingsos);
					ObservableMap<Integer, Integer> underlyingbos = FXCollections.observableMap(new HashMap<>());
					ObsMapHolderImpl<Integer, Integer> newmarketBOs = new ObsMapHolderImpl<>(underlyingbos);
					getMarketOrders().follow((map) -> {
						HashMap<Integer, Integer> newMapsos = new HashMap<>();
						HashMap<Integer, Integer> newMapbos = new HashMap<>();
						for (R_get_characters_character_id_orders v : map.values()) {
							if (!v.is_buy_order) {
								newMapsos.put(v.type_id, newMapsos.getOrDefault(v.type_id, 0) + v.volume_remain);
							} else {
								newMapbos.put(v.type_id, newMapbos.getOrDefault(v.type_id, 0) + v.volume_remain);
							}
						}
						synchronized (underlyingsos) {
							underlyingsos.keySet().retainAll(newMapsos.keySet());
							underlyingsos.putAll(newMapsos);
						}
						newmarketSOs.dataReceived();
						synchronized (underlyingbos) {
							underlyingbos.keySet().retainAll(newMapbos.keySet());
							underlyingbos.putAll(newMapbos);
						}
						newmarketBOs.dataReceived();
					});
					marketSOs = newmarketSOs;
					marketBOs = newmarketBOs;
				}
			});
		}
	}

	public ObsMapHolder<Integer, Integer> getMarketSOs() {
		makeBOSOs();
		return marketSOs;
	}

	public ObsMapHolder<Integer, Integer> getMarketBOs() {
		makeBOSOs();
		return marketBOs;
	}

	private ObsMapHolder<Long, R_get_characters_character_id_orders> cacheOrders = null;

	public ObsMapHolder<Long, R_get_characters_character_id_orders> getMarketOrders() {
		if (cacheOrders == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cacheOrders == null) {
					cacheOrders = ObsMapHolderImpl.toMap(con.raw.cache.characters.orders(con.characterId()), o -> o.order_id);
				}
			});
		}
		return cacheOrders;
	}

	//
	// wallet
	//
	/** get total isk balance */
	public ObsObjHolder<Double> getWallet() {
		return con.raw.cache.characters.wallet(con.characterId());
	}

	private ObsMapHolder<String, R_get_characters_character_id_wallet_transactions> walletTransactions;

	/**
	 * get wallet history.<br />
	 * The key is String because a transaction can appear in the corporation and
	 * character wallets, with same id.
	 */
	public ObsMapHolder<String, R_get_characters_character_id_wallet_transactions> getWalletTransactions() {
		if (walletTransactions == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (walletTransactions == null) {
					walletTransactions = con.raw.cache.characters.wallet_transactions(con.characterId(), null)
							.toMap(h -> "" + con.characterId() + h.transaction_id);
				}
			});
		}
		return walletTransactions;
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
}
