package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.assets.ItemNode;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.Universe.GroupCache;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_post_assets_names_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_type;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class Assets {

	@Getter
	@Accessors(fluent = true)
	private final ESIAccount con;

	protected boolean canName(ItemNode asset, int groupId) {
		if (!asset.is_singleton) {
			return false;
		}
		GroupCache groupIds = ESIAccess.INSTANCE.universe.groupIds;
		if (groupIds.ofAbstract().contains(asset.groupId)
				|| groupIds.ofBlueprints().contains(asset.groupId)
				|| groupIds.ofModules().contains(asset.groupId)
				|| groupIds.ofStations().contains(asset.groupId)
				|| groupIds.ofStructuresModules().contains(asset.groupId)
				|| groupIds.ofSubsystems().contains(asset.groupId)) {
			return false;
		}
		return true;
	}

	private static final int NAMINGCALL_MAXIDS = 1000;

	// get the names of specific assets, and store them as optional
	protected void name(Map<Long, ItemNode> items, IntUnaryOperator typeId2groupId) {
		if (items == null || items.size() == 0) {
			return;
		}
		ArrayList<M_post_assets_names_2> nameResults = new ArrayList<>();
		long[] ids = items.values().stream().filter(it -> canName(it, typeId2groupId.applyAsInt(it.type_id)))
				.mapToLong(item -> item.item_id).toArray();
		for (int start = 0; start < ids.length; start += NAMINGCALL_MAXIDS) {
			long[] ids2 = Arrays.copyOfRange(ids, start, Math.min(start + NAMINGCALL_MAXIDS, ids.length));
			Requested<M_post_assets_names_2[]> names = con.connection().post_characters_assets_names(con.characterId(), ids2,
					null);
			while (names.isServerError()) {
				names = con.connection().post_characters_assets_names(con.characterId(), ids2, null);
			}
			if (names.isOk()) {
				nameResults.addAll(Arrays.asList(names.getOK()));
			} else {
				System.err.println(" error " + names.getError() + " response=" + names.getResponseCode());
			}
		}

		for (M_post_assets_names_2 item : nameResults) {
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
	public ListHolder<R_get_characters_character_id_assets> getList() {
		// caching is already present at the cache level.
		return con.connection().cache().characters.assets(con.characterId());
	}

	public static record ItemForest(Map<Long, ItemNode> itemsByID,
			Map<Location, Map<get_corporations_corporation_id_assets_location_flag, List<ItemNode>>> roots) {

		public ItemForest() {
			this(new HashMap<>(), new HashMap<>());
		}
	}

	protected ItemForest grow(List<R_get_characters_character_id_assets> assets,
			IntUnaryOperator typeId2groupId,
			IntFunction<String> typeId2name) {
		ItemForest ret = new ItemForest();
		for (R_get_characters_character_id_assets item : assets) {
			ret.itemsByID.put(item.item_id, new ItemNode(item, typeId2groupId, typeId2name));
		}

		// fetch the names
		name(ret.itemsByID, typeId2groupId);

		// place the items in the roots.
		for (ItemNode itemNode : ret.itemsByID.values()) {
			ItemNode parent = ret.itemsByID.get(itemNode.location_id);
			if (parent == null) {
				Location location = Location.resolve(con, itemNode.location_id);
				ret.roots.computeIfAbsent(location, _ -> new HashMap<>())
						.computeIfAbsent(itemNode.location_flag, _ -> new ArrayList<>()).add(itemNode);
			} else {
				parent.contained.computeIfAbsent(itemNode.location_flag, _ -> new ArrayList<>()).add(itemNode);
			}
		}
		return ret;
	}

	private ObjHolder<ItemForest> forest = null;

	public synchronized ObjHolder<ItemForest> getForest(IntUnaryOperator typeId2groupId,
			IntFunction<String> typeId2name) {
		if (forest == null) {
			forest = getList().map(l -> grow(l, typeId2groupId, typeId2name));
		}
		return forest;
	}

	/**
	 * get the available map of assets locations to assets id to assets quantity.
	 * <br />
	 * The assets for a location are actually unmodifiable maps.
	 */
	@Getter(lazy = true)
	private final MapHolder<Long, Map<Integer, Long>> available = getList().mapMap(l -> availableAssetsByLocation(l));

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
				.collect(Collectors.toMap(i -> i.item_id, i -> i.location_id, (l1, _) -> l1));
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
		return switch (source) {
			case CorpseBay -> get_corporations_corporation_id_assets_location_flag.CrateLoot;
			default -> get_corporations_corporation_id_assets_location_flag.valueOf(source.name());
		};
	}

	public static get_corporations_corporation_id_assets_location_type convert(
			get_characters_character_id_assets_location_type source) {
		return switch (source) {
			case item -> get_corporations_corporation_id_assets_location_type.item;
			case other -> get_corporations_corporation_id_assets_location_type.other;
			case solar_system -> get_corporations_corporation_id_assets_location_type.solar_system;
			case station -> get_corporations_corporation_id_assets_location_type.station;
			default -> throw new UnsupportedOperationException("can't handle case " + source);
		};
	}

}
