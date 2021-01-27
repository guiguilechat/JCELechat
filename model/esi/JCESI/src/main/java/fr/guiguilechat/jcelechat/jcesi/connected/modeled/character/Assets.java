package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag.AutoFit;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag.Deliveries;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag.Hangar;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag.HangarAll;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag.Locked;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag.ShipHangar;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag.Unlocked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_post_assets_names_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_type;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class Assets {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Assets.class);

	@Getter
	@Accessors(fluent = true)
	private final ESIAccount con;

	protected static boolean canName(R_get_corporations_corporation_id_assets asset) {
		return asset.is_singleton && asset.location_flag != get_corporations_corporation_id_assets_location_flag.AutoFit;
	}

	// get the names of specific assets
	protected void name(Map<Long, ItemNode> items) {
		if (items == null || items.size() == 0) {
			return;
		}
		ArrayList<M_post_assets_names_2> ret = new ArrayList<>();
		long[] ids = items.values().stream().filter(Assets::canName).mapToLong(item -> item.item_id).toArray();
		int start = 0;
		while (start < ids.length) {
			long[] ids2 = Arrays.copyOfRange(ids, start, Math.min(start + 1000, ids.length));
			Requested<M_post_assets_names_2[]> names = con.connection().post_characters_assets_names(con.characterId(), ids2,
					null);
			while (names.isServerError()) {
				names = con.connection().post_characters_assets_names(con.characterId(), ids2, null);
			}
			if (names.isOk()) {
				ret.addAll(Arrays.asList(names.getOK()));
			} else {
				System.err.println(" error " + names.getError() + " response=" + names.getResponseCode());
			}
			start += 1000;
		}

		for (M_post_assets_names_2 item : ret) {
			if (item.name != null) {
				items.get(item.item_id).withOptional(item.name);
			}
		}
	}

	/**
	 *
	 * @return the raw cached observable list of assets for this character, from
	 *         ESI.
	 */
	public ObsListHolder<R_get_characters_character_id_assets> getList() {
		// caching is already present at the cache level.
		return con.connection().cache().characters.assets(con.characterId());
	}

	/**
	 * an {@link R_get_corporations_corporation_id_assets} that also knows its
	 * children
	 *
	 */
	public static class ItemNode extends R_get_corporations_corporation_id_assets {
		public Map<get_corporations_corporation_id_assets_location_flag, List<ItemNode>> contained = new HashMap<>();

		public ItemNode(boolean is_blueprint_copy, boolean is_singleton, long item_id,
				get_corporations_corporation_id_assets_location_flag location_flag, long location_id,
				get_corporations_corporation_id_assets_location_type location_type, int quantity, int type_id) {
			this.is_blueprint_copy = is_blueprint_copy;
			this.is_singleton = is_singleton;
			this.item_id = item_id;
			this.location_flag = location_flag;
			this.location_id = location_id;
			this.location_type = location_type;
			this.quantity = quantity;
			this.type_id = type_id;
			// precache the type for when needed.
			ESIAccess.INSTANCE.universe.cache().types(type_id);
		}

		public ItemNode(R_get_characters_character_id_assets source) {
			this(source.is_blueprint_copy, source.is_singleton, source.item_id, convert(source.location_flag), source.location_id,
					convert(source.location_type), source.quantity, source.type_id);
		}

		public ItemNode(R_get_corporations_corporation_id_assets source) {
			this(source.is_blueprint_copy, source.is_singleton, source.item_id, source.location_flag, source.location_id,
					source.location_type, source.quantity, source.type_id);
		}

		private transient R_get_universe_types_type_id type = null;

		public R_get_universe_types_type_id type() {
			if (type == null) {
				type = ESIAccess.INSTANCE.universe.cache().types(type_id).get();
			}
			return type;
		}

		private String optional = null;

		public ItemNode withOptional(String optional) {
			this.optional = optional;
			name = null;
			return this;
		}

		private transient String name = null;

		public String name() {
			if (name == null) {
				if (optional == null) {
					name = type().name;
				} else {
					name = "["+optional+"] "+type().name;
				}
			}
			return name;
		}

		private transient Double priceAverage = null;

		public double priceAverage() {
			if (priceAverage == null) {
				if (is_blueprint_copy) {
					priceAverage = 0.0;
				} else {
					priceAverage = quantity * ESIAccess.INSTANCE.markets.getAverage(type_id);
				}
			}
			return priceAverage;
		}

		private transient Double recPriceAverage = null;

		public double recPriceAverage() {
			if (recPriceAverage == null) {
				double total = priceAverage();
				for (List<ItemNode> list : contained.values()) {
					for (ItemNode itemnode : list) {
						total += itemnode.recPriceAverage();
					}
				}
				recPriceAverage = total;
			}
			return recPriceAverage;
		}

		public void print(StringBuilder sb, String prefix, String spacing, String newline) {
			sb.append(prefix).append(name());
			if (quantity != 1) {
				sb.append(" ×").append(quantity);
			}
			// sb.append('[').append(item_id).append('@').append(location_id).append(']');
			prefix = prefix + spacing;
			for (Entry<get_corporations_corporation_id_assets_location_flag, List<ItemNode>> e : contained.entrySet()) {
				if (!e.getValue().isEmpty()) {
					sb.append(newline).append(prefix).append(e.getKey());
					for (ItemNode i : e.getValue()) {
						sb.append(newline);
						i.print(sb, prefix + spacing, spacing, newline);
					}
				}
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			print(sb, "", "\t", "\n");
			return sb.toString();
		}
	}

	public static class ItemForest {
		public Map<Long, ItemNode> itemsByID = new HashMap<>();
		public Map<Location, Map<get_corporations_corporation_id_assets_location_flag, List<ItemNode>>> roots = new HashMap<>();
	}

	protected ItemForest grow(List<R_get_characters_character_id_assets> assets) {
		ItemForest ret = new ItemForest();
		for (R_get_characters_character_id_assets item : assets) {
			ret.itemsByID.put(item.item_id, new ItemNode(item));
		}

		// fetch the names
		name(ret.itemsByID);

		// place the items in the roots.
		for (ItemNode itemNode : ret.itemsByID.values()) {
			ItemNode parent = ret.itemsByID.get(itemNode.location_id);
			if (parent == null) {
				Location location = Location.resolve(con, itemNode.location_id);
				ret.roots.computeIfAbsent(location, loc -> new HashMap<>())
				.computeIfAbsent(itemNode.location_flag, f -> new ArrayList<>()).add(itemNode);
			} else {
				parent.contained.computeIfAbsent(itemNode.location_flag, f -> new ArrayList<>()).add(itemNode);
			}
		}
		return ret;
	}

	private transient ObsObjHolder<ItemForest> cacheForest = null;

	public ObsObjHolder<ItemForest> getForest() {
		if (cacheForest == null) {
			ObsListHolder<R_get_characters_character_id_assets> list = getList();
			synchronized (list) {
				if (cacheForest == null) {
					cacheForest = list.map(this::grow);
				}
			}
		}
		return cacheForest;
	}

	/**
	 * called when a change happens to the list of assets. When this happens, we
	 * recreate the whole map and put it back .
	 *
	 * @param c
	 * @param map
	 */
	protected void applyNewAssets(Change<? extends R_get_characters_character_id_assets> c,
			ObservableMap<Long, ObservableMap<Integer, Long>> map) {
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

		Map<Long, Map<Integer, Long>> newitems = Stream.of(itemsArr)
				.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), Assets::makeMap, Assets::mergeMap));
		synchronized (map) {
			map.keySet().retainAll(newitems.keySet());
			for (Entry<Long, Map<Integer, Long>> e : newitems.entrySet()) {
				ObservableMap<Integer, Long> om = map.get(e.getKey());
				if (om == null) {
					om = FXCollections.observableHashMap();
					map.put(e.getKey(), om);
				}
				om.keySet().retainAll(e.getValue().keySet());
				om.putAll(e.getValue());
			}
		}
	}

	private transient ObsMapHolder<Long, Map<Integer, Long>> availableAssets = null;

	/**
	 * get the available map of assets locations to assets id to assets quantity.
	 * <br />
	 * The assets for a location are actually unmodifiable maps.
	 *
	 * @return a cached observable map.
	 */
	public ObsMapHolder<Long, Map<Integer, Long>> getAvailable() {
		if (availableAssets == null) {
			ObsListHolder<R_get_characters_character_id_assets> assetList = getList();
			synchronized (assetList) {
				if (availableAssets == null) {
					ObservableMap<Long, Map<Integer, Long>> internal = FXCollections.observableMap(new LinkedHashMap<>());
					ObsMapHolderImpl<Long, Map<Integer, Long>> ret = new ObsMapHolderImpl<>(internal);
					assetList.follow(l -> {
						Map<Long, Map<Integer, Long>> newmap = availableAssetsByLocation(l);
						logger.debug("character " + con.characterName() + " has available assets " + newmap);
						internal.keySet().retainAll(newmap.keySet());
						for (Entry<Long, Map<Integer, Long>> e : newmap.entrySet()) {
							if (!e.getValue().equals(internal.get(e.getKey()))) {
								internal.put(e.getKey(), Collections.unmodifiableMap(e.getValue()));
							}
						}
						ret.dataReceived();
					});
					availableAssets = ret;
				}
			}
		}
		return availableAssets;
	}

	private static final HashSet<get_characters_character_id_assets_location_flag> availableAssetsFlags = new HashSet<>(
			Arrays.asList(AutoFit, Deliveries, Hangar, HangarAll, Locked, ShipHangar, Unlocked));

	/**
	 * filter and group the assets from an asset lists
	 *
	 * @param assets
	 *          the list of assets
	 * @return the map locationid -> typeid -> qtty
	 */
	public static Map<Long, Map<Integer, Long>> availableAssetsByLocation(
			Iterable<R_get_characters_character_id_assets> assets) {
		// remove all the items that have a bad location_flag
		R_get_characters_character_id_assets[] itemsArr = StreamSupport.stream(assets.spliterator(), false)
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
		Map<Long, Map<Integer, Long>> ret = Stream.of(itemsArr)
				.filter(asset -> !asset.is_singleton && availableAssetsFlags.contains(asset.location_flag))
				.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), Assets::makeMap, Assets::mergeMap));
		return ret;
	}

	private static Map<Integer, Long> makeMap(R_get_characters_character_id_assets asset) {
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

	public static get_corporations_corporation_id_assets_location_flag convert(
			get_characters_character_id_assets_location_flag source) {
		switch (source) {
		case CorpseBay:
			return get_corporations_corporation_id_assets_location_flag.CrateLoot;
		default:
			return get_corporations_corporation_id_assets_location_flag.valueOf(source.name());
		}
	}

	public static get_corporations_corporation_id_assets_location_type convert(
			get_characters_character_id_assets_location_type source) {
		switch (source) {
		case item:
			return get_corporations_corporation_id_assets_location_type.item;
		case other:
			return get_corporations_corporation_id_assets_location_type.other;
		case solar_system:
			return get_corporations_corporation_id_assets_location_type.solar_system;
		case station:
			return get_corporations_corporation_id_assets_location_type.station;
		default:
			throw new UnsupportedOperationException("can't handle case " + source);
		}
	}

}
