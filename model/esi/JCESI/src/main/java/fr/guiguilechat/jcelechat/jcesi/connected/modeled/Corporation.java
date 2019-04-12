package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.CorpBookmarks;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_structures;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.lelouet.collectionholders.impl.ObsMapHolderImpl;
import fr.lelouet.collectionholders.impl.ObsObjHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Corporation {

	protected final ESIAccount con;

	public Corporation(ESIAccount con) {
		this.con = con;
		bms = new CorpBookmarks(con);
	}

	public final CorpBookmarks bms;

	// industry jobs

	public ObsMapHolder<Integer, R_get_corporations_corporation_id_industry_jobs> getIndustryJobs() {
		return ObsMapHolderImpl.toMap(
				con.raw.cache.corporations.industry_jobs(con.character.infos.corporationId().get(), false), j -> j.job_id);
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

	public static boolean isInvetion(R_get_corporations_corporation_id_industry_jobs job) {
		return job.activity_id == 8;
	}

	/**
	 *
	 * @return the location->typeid->quantity
	 */
	public ObservableMap<Long, ObservableMap<Integer, Integer>> getAssets() {
		ObservableMap<Long, ObservableMap<Integer, Integer>> assets = FXCollections
				.observableMap(new LinkedHashMap<>());
		R_get_corporations_corporation_id_assets[] itemsArr = con.raw.cache.corporations
				.assets(con.character.infos.corporationId().get()).copy()
				.stream()
				.filter(asset -> !get_corporations_corporation_id_assets_location_flag.AutoFit.equals(asset.location_flag))
				.toArray(R_get_corporations_corporation_id_assets[]::new);
		// we make the map of itemid->locations. if a location is actually an
		// asset, we iterally map it to this asset's location instead
		Map<Long, Long> baseLocationMap = Stream.of(itemsArr)
				.collect(Collectors.toMap(i -> i.item_id, i -> i.location_id));
		Map<Long, Long> idToLocation = baseLocationMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, e -> {
					Long ret = e.getValue();
					while (baseLocationMap.containsKey(ret)) {
						ret = baseLocationMap.get(ret);
					}
					return ret;
				}));
		Map<Long, Map<Integer, Integer>> newitems = Stream.of(itemsArr)
				.collect(Collectors.toMap(a -> idToLocation.get(a.item_id), Corporation::makeMap, Corporation::mergeMap));

		for (Entry<Long, Map<Integer, Integer>> e : newitems.entrySet()) {
			ObservableMap<Integer, Integer> om = assets.get(e.getKey());
			if (om == null) {
				om = FXCollections.observableHashMap();
				assets.put(e.getKey(), om);
			}
			om.keySet().retainAll(e.getValue().keySet());
			om.putAll(e.getValue());
		}
		return assets;
	}

	private static Map<Integer, Integer> makeMap(R_get_corporations_corporation_id_assets asset) {
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

	public ObsMapHolder<Long, R_get_corporations_corporation_id_blueprints> getBlueprints() {
		return ObsMapHolderImpl.toMap(con.raw.cache.corporations.blueprints(con.character.infos.corporationId().get()),
				bp -> bp.item_id);
	}

	private ObsMapHolder<Long, R_get_corporations_corporation_id_structures> structures = null;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_structures> getStructures() {
		if(structures==null) {
			synchronized (this) {
				if(structures==null) {
					structures = ObsMapHolderImpl.toMap(
							con.raw.cache.corporations.structures(con.character.infos.corporationId().get()),
							str -> str.structure_id);
				}
			}
		}
		return structures;
	}

	ObsObjHolderImpl<Double> wallet = null;

	/** get the total sum of all the wallets */
	public ObsObjHolder<Double> getWallet() {
		if (wallet == null) {
			synchronized (this) {
				if (wallet == null) {
					SimpleObjectProperty<Double> underlying = new SimpleObjectProperty<>();
					wallet = new ObsObjHolderImpl<>(underlying);
					con.raw.cache.corporations.wallets(con.character.infos.corporationId().get()).follow(c -> {
						double delta = 0;
						while (c.next()) {
							delta += c.getAddedSubList().stream().mapToDouble(w1 -> w1.balance).sum()
									- c.getRemoved().stream().mapToDouble(w2 -> w2.balance).sum();
						}
						underlying.set(underlying.get() == null ? delta : underlying.get() + delta);
					});
				}
			}
		}
		return wallet;
	}

	public R_get_corporations_corporation_id getInformations() {
		return ESIStatic.INSTANCE.cache.corporations.get(con.character.infos.corporationId().intValue()).get();
	}
}
