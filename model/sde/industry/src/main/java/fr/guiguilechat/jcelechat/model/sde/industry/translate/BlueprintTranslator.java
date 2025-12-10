package fr.guiguilechat.jcelechat.model.sde.industry.translate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.type.ConsumedMaterial;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.Eblueprints;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.Eblueprints.Activities;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.Eblueprints.Activities.Activity.Product;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporations;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeMaterials;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeMaterials.Material;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.Activity;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BlueprintTranslator {

	private final ClientCache clientCache;

	protected static Etypes type(int id) {
		return Etypes.LOADER.yaml().get(id);
	}

	List<KeyValTime<Eblueprints>> listBlueprints()
			throws JsonMappingException, JsonProcessingException, SQLException {
		return Eblueprints.getLoader().load(clientCache);
	}

	public void translateBlueprints(ClientCache cc, LinkedHashMap<Integer, Blueprint> blueprints,
			LinkedHashMap<Integer, IndustryUsage> usages)
			throws JsonMappingException, JsonProcessingException, SQLException {
		// set of type ids that are seeded by NPCs
		Set<Integer> seededItems = EnpcCorporations.LOADER.yaml().load().values().stream()
				.flatMap(crp -> crp.corporationTrades.keySet().stream())
				.collect(Collectors.toSet());
		for (KeyValTime<Eblueprints> e : listBlueprints()) {
			int bpId = e.getVal().blueprintTypeId;
			Etypes bpType = type(bpId);
			if (bpType == null) {
				log.warn("skipping null-type bp id=" + bpId + ", likely not published ");
				continue;
			}
			if (!bpType.published) {
				log.debug("skipping unpublished bp " + bpType.name + "(" + bpId + ")");
				continue;
			}
			Blueprint translated = makeBlueprint(e.getVal());
			translated.seeded = seededItems.contains(translated.id) && !translated.name().endsWith("II Blueprint");
			blueprints.put(bpId, translated);
			addUsages(translated, usages);
		}

		for (Entry<Integer, EtypeMaterials> e : EtypeMaterials.LOADER.yaml().load().entrySet()) {
			Etypes etype = type(e.getKey());
			if (etype == null) {
				log.warn("can't find type id=" + e.getKey() + " reprocessed into " + e.getValue());
				continue;
			}
			int portionSize = etype.portionSize;
			IndustryUsage usage = usages.computeIfAbsent(e.getKey(), _ -> new IndustryUsage());
			for (Material mat : e.getValue().materials) {
				Etypes outputmat = type(mat.materialTypeID);
				if (outputmat == null) {
					log.error("can't find type id " + mat.materialTypeID + " reprocessed from " + etype.enName());
					continue;
				}
				usage.reprocessInto.put(mat.materialTypeID, 1.0 * mat.quantity / portionSize);
				usages.computeIfAbsent(mat.materialTypeID, _ -> new IndustryUsage()).reprocessedFrom
						.add(e.getKey());
			}
		}

		// sort the usages by item name
		ArrayList<Entry<Integer, IndustryUsage>> l = new ArrayList<>(usages.entrySet());
		Collections.sort(l, Comparator.comparing(Entry<Integer, IndustryUsage>::getKey));
		usages.clear();
		for (Entry<Integer, IndustryUsage> e : l) {
			usages.put(e.getKey(), e.getValue());
		}
	}

	Blueprint makeBlueprint(Eblueprints bp) {
		Blueprint bp2 = new Blueprint();
		bp2.id = bp.blueprintTypeId;
		bp2.maxCopyRuns = bp.maxProductionLimit;
		bp2.copying = convertActivity(bp.activities.copying);
		bp2.invention = convertActivity(bp.activities.invention);
		bp2.manufacturing = convertActivity(bp.activities.manufacturing);
		bp2.research_material = convertActivity(bp.activities.researchMaterial);
		bp2.research_time = convertActivity(bp.activities.researchTime);
		bp2.reaction = convertActivity(bp.activities.reaction);
		return bp2;
	}

	/**
	 * try to convert an SDE activity
	 *
	 * @param listedActivity
	 *                the SDE activity
	 * @return null if a type referred to in the activity was not found.
	 */
	Activity convertActivity(Activities.Activity listedActivity) {
		if (listedActivity == null) {
			return null;
		}
		AtomicBoolean skip = new AtomicBoolean(false);
		Activity ret = new Activity();
		ret.time = listedActivity.time;
		listedActivity.materials.stream().map(this::convertMaterialReq)
				.peek(o -> {
					if (o == null) {
						skip.set(true);
					}
				})
				.filter(o -> o != null)
				.sorted(Comparator.comparing(m1 -> m1.id))
				.forEach(ret.materials::add);
		listedActivity.products.stream().map(this::convertMaterialProd)
				.peek(o -> {
					if (o == null) {
						skip.set(true);
					}
				})
				.filter(o -> o != null)
				.sorted(Comparator.comparing(m1 -> m1.id))
				.forEach(ret.products::add);
		listedActivity.skills.stream().sorted(Comparator.comparing(s1 -> s1.typeId)).forEach(s -> {
			Etypes skill = type(s.typeId);
			if (skill == null) {
				log.error("missing skill " + s.typeId);
				skip.set(true);
			} else {
				TypeRef<Skill> ref = new TypeRef<>();
				ref.id = s.typeId;
				ret.skills.put(ref, s.level);
			}
		});
		if (skip.get()) {
			return null;
		}
		return ret;
	}

	MaterialReq<?> convertMaterialReq(ConsumedMaterial sdeMat) {
		Etypes item = type(sdeMat.typeId);
		if (item != null) {
			MaterialReq<?> ret = new MaterialReq<>();
			ret.quantity = sdeMat.quantity;
			ret.id = sdeMat.typeId;
			return ret;
		} else {
			log.error("missing type id=" + sdeMat.typeId + " for material conversion");
			return null;
		}
	}

	MaterialProd<?> convertMaterialProd(Product sdeMat) {
		Etypes item = type(sdeMat.typeId);
		if (item != null) {
			MaterialProd<?> ret = new MaterialProd<>();
			ret.quantity = sdeMat.quantity;
			ret.id = sdeMat.typeId;
			ret.probability = sdeMat.probability == null ? 1.0f : sdeMat.probability.floatValue();
			return ret;
		} else {
			log.error("missing type id=" + sdeMat.typeId + " for product conversion");
			return null;
		}
	}

	/**
	 * add the usage of a bp into the map of usages : for each activity, add the
	 * bp as using the materials of that activity
	 */
	void addUsages(Blueprint bp, Map<Integer, IndustryUsage> usages) {
		if (bp.manufacturing != null) {
			addUsages(bp.id, usages, bp.manufacturing.products, u -> u.productOfManuf);
			addUsages(bp.id, usages, bp.manufacturing.materials, u -> u.materialInManuf);
		}
		if (bp.copying != null) {
			addUsages(bp.id, usages, bp.copying.materials, u -> u.materialInCopy);
		}
		if (bp.invention != null) {
			addUsages(bp.id, usages, bp.invention.materials, u -> u.materialInInvention);
			addUsages(bp.id, usages, bp.invention.products, u -> u.productOfInvention);
		}
		if (bp.research_material != null) {
			addUsages(bp.id, usages, bp.research_material.materials, u -> u.materialInME);
		}
		if (bp.research_time != null) {
			addUsages(bp.id, usages, bp.research_time.materials, u -> u.materialInTE);
		}
		if (bp.reaction != null) {
			addUsages(bp.id, usages, bp.reaction.materials, u -> u.materialInReaction);
			addUsages(bp.id, usages, bp.reaction.products, u -> u.productOfReaction);
		}
	}

	@SuppressWarnings("rawtypes")
	void addUsages(Integer bpoID, Map<Integer, IndustryUsage> usages,
			List<? extends MaterialReq> materials, Function<IndustryUsage, Set<Integer>> categorizer) {
		if (materials == null || materials.isEmpty()) {
		} else {
			for (MaterialReq<?> m : materials) {
				if (m == null) {
					log.warn("null material in list of bp id=" + bpoID + " : " + materials);
				}
				IndustryUsage u = usages.computeIfAbsent(m.id, _ -> new IndustryUsage());
				categorizer.apply(u).add(bpoID);
			}
		}
	}

}
