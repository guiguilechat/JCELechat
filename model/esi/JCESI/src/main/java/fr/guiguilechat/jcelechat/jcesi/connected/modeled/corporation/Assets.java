package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.AutoFit;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.CorpDeliveries;
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
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.Impounded;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.Locked;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.ShipHangar;
import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.Unlocked;

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

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Assets.ItemForest;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Assets.ItemNode;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Assets {

	private final ESIAccount con;

	public Assets(ESIAccount con) {
		this.con = con;
	}

	public ObsListHolder<R_get_corporations_corporation_id_assets> getList() {
		// caching is already present at the cache level.
		return con.raw.cache.corporations.assets(con.corporation.getId());
	}

	protected ItemForest grow(List<R_get_corporations_corporation_id_assets> assets) {
		ItemForest ret = new ItemForest();
		for (R_get_corporations_corporation_id_assets item : assets) {
			ret.itemsByID.put(item.item_id, new ItemNode(item));
		}
		for (ItemNode itemNode : ret.itemsByID.values()) {
			ItemNode parent = ret.itemsByID.get(itemNode.location_id);
			if (parent == null) {
				Location location = Location.resolve(con, itemNode.location_id);
				ret.roots.computeIfAbsent(location, loc -> new ArrayList<>()).add(itemNode);
			} else {
				parent.contained.computeIfAbsent(itemNode.location_flag, f -> new ArrayList<>()).add(itemNode);
			}
		}
		return ret;
	}

	private ObsObjHolder<ItemForest> cacheForest = null;

	public ObsObjHolder<ItemForest> getForest() {
		if (cacheForest == null) {
			ObsListHolder<R_get_corporations_corporation_id_assets> list = getList();
			synchronized (list) {
				if (cacheForest == null) {
					cacheForest = list.map(this::grow);
				}
			}
		}
		return cacheForest;
	}

	private ObsMapHolder<Long, Map<Integer, Long>> availableAssets = null;

	public ObsMapHolder<Long, Map<Integer, Long>> getAvailableAssets() {
		if (availableAssets == null) {
			ObsListHolder<R_get_corporations_corporation_id_assets> assetList = getList();
			synchronized (assetList) {
				if (availableAssets == null) {
					ObservableMap<Long, Map<Integer, Long>> internal = FXCollections.observableMap(new LinkedHashMap<>());
					ObsMapHolderImpl<Long, Map<Integer, Long>> ret = new ObsMapHolderImpl<>(internal);
					assetList.follow(l -> {
						Map<Long, Map<Integer, Long>> newmap = availableAssetsByLocation(l);
						boolean modification = internal.keySet().retainAll(newmap.keySet()) || newmap.isEmpty();
						for (Entry<Long, Map<Integer, Long>> e : newmap.entrySet()) {
							var thenew = e.getValue();
							var old = internal.get(e.getKey());
							if (!thenew.equals(old)) {
								internal.put(e.getKey(), Collections.unmodifiableMap(thenew));
								modification = true;
							}
						}
						if (modification) {
							// System.err.println("corporation " + getName() + " modification
							// of assetsbyloc");
							ret.dataReceived();
						} else {
							// System.err.println("corporation " + getName() + " keeps same
							// assetsbyloc");
						}
					});
					availableAssets = ret;
				}
			}
		}
		return availableAssets;
	}

	private static final HashSet<get_corporations_corporation_id_assets_location_flag> availableAssetsFlags = new HashSet<>(
			Arrays.asList(AutoFit, CorpDeliveries, CorpSAG1, CorpSAG2, CorpSAG3, CorpSAG4, CorpSAG5, CorpSAG6, CorpSAG7,
					Deliveries, Hangar, HangarAll, Impounded, Locked, ShipHangar, Unlocked));

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
			Long location = e.getValue();
			while (baseLocationMap.containsKey(location)) {
				location = baseLocationMap.get(location);
			}
			return location;
		}));
		Map<Long, Map<Integer, Long>> ret = Stream.of(itemsArr)
				.filter(asset -> !asset.is_singleton && availableAssetsFlags.contains(asset.location_flag))
				.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), Assets::makeMap, Assets::mergeMap));
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

}
