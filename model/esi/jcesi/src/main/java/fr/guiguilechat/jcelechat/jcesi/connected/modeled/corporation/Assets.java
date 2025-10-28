package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import static fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Assets.ItemForest;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Assets.ItemNode;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.Universe.GroupCache;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_post_assets_names_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import lombok.Getter;

public class Assets {

	private final ESIAccount con;

	public Assets(ESIAccount con) {
		this.con = con;
	}

	protected boolean canName(ItemNode asset) {
		if (!asset.is_singleton) {
			return false;
		}
		GroupCache groupIds = ESIAccess.INSTANCE.universe.groupIds;
		if (groupIds.ofAbstract().contains(asset.type().group_id)
				|| groupIds.ofBlueprints().contains(asset.type().group_id)
				|| groupIds.ofModules().contains(asset.type().group_id)
				|| groupIds.ofStations().contains(asset.type().group_id)
				|| groupIds.ofStructuresModules().contains(asset.type().group_id)
				|| groupIds.ofSubsystems().contains(asset.type().group_id)) {
			return false;
		}
		return true;
	}

	private static final int NAMINGCALL_MAXIDS = 1000;

	/**
	 * get the names of specific assets and assign them as optional
	 */
	protected void name(Map<Long, ItemNode> items) {
		if (items == null || items.size() == 0) {
			return ;
		}
		ArrayList<M_post_assets_names_2> nameResults = new ArrayList<>();
		long[] ids = items.values().stream().filter(this::canName).mapToLong(item -> item.item_id).toArray();
		List<ItemNode> errored = new ArrayList<>();
		for (int start = 0; start < ids.length; start += NAMINGCALL_MAXIDS) {
			long[] ids2 = Arrays.copyOfRange(ids, start, Math.min(start + NAMINGCALL_MAXIDS, ids.length));
			Requested<M_post_assets_names_2[]> names = con.connection().post_corporations_assets_names(
					con.corporation.getId(), ids2,
					null);
			while (names.isServerError()) {
				names = con.connection().post_corporations_assets_names(con.corporation.getId(), ids2, null);
			}
			if (names.isOk()) {
				nameResults.addAll(Arrays.asList(names.getOK()));
			} else {
				System.err.println(" error\t" + names.getError() + "\tresponse=" + names.getResponseCode());
				for (long id : ids2) {
					ItemNode in = items.get(id);
					errored.add(in);
					System.err.println("" + in.item_id + "\t" + in.location_flag + "\t" + in.type_id + "\t" + in.type().group_id
							+ "\t" + in.type().name);
				}
				Integer remainingErrors = names.getRemainingErrors();
				if (remainingErrors != null && remainingErrors < 1) {
					try {
						System.out.println("sleeping " + names.getErrorsReset() + " seconds");
						Thread.sleep(1000 * names.getErrorsReset());
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		for(M_post_assets_names_2 item : nameResults) {
			items.get(item.item_id).withOptional(item.name);
		}
		if (!errored.isEmpty()) {
			System.err.println("item\tlocation\ttype_id\tgroup_id\ttype_name");
			for (ItemNode in : errored) {
				System.err.println("" + in.item_id + "\t" + in.location_flag + "\t" + in.type_id + "\t" + in.type().group_id
						+ "\t" + in.type().name);
			}
		}
	}

	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_assets> list = con.connection().cache().corporations
	.assets(con.corporation.getId());

	protected ItemForest grow(List<R_get_corporations_corporation_id_assets> assets) {
		ItemForest ret = new ItemForest();
		for (R_get_corporations_corporation_id_assets item : assets) {
			ret.itemsByID().put(item.item_id, new ItemNode(item));
		}
		// fetch the names
		name(ret.itemsByID());

		// place the items in the roots.
		for (ItemNode itemNode : ret.itemsByID().values()) {
			ItemNode parent = ret.itemsByID().get(itemNode.location_id);
			if (parent == null) {
				Location location = Location.resolve(con, itemNode.location_id);
				ret.roots().computeIfAbsent(location, _ -> new HashMap<>())
						.computeIfAbsent(itemNode.location_flag, _ -> new ArrayList<>()).add(itemNode);
			} else {
				parent.contained.computeIfAbsent(itemNode.location_flag, _ -> new ArrayList<>()).add(itemNode);
			}
		}
		return ret;
	}

	@Getter(lazy = true)
	private final ObjHolder<ItemForest> forest = getList().map(this::grow);

	@Getter(lazy = true)
	private final MapHolder<Long, Map<Integer, Long>> available = getList().mapMap(l -> availableAssetsByLocation(l));

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
				.collect(Collectors.toMap(i -> i.item_id, i -> i.location_id, (l1, _) -> l1));
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
