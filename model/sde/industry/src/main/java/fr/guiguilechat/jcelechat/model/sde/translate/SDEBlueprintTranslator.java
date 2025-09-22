package fr.guiguilechat.jcelechat.model.sde.translate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.Activity;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials.Material;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SDEBlueprintTranslator {

	public void translateBlueprints(LinkedHashMap<Integer, Blueprint> blueprints,
			LinkedHashMap<Integer, IndustryUsage> usages) {
		// set of type ids that are seeded by NPCs
		Set<Integer> seededItems = EnpcCorporations.LOADER.load().values().stream()
				.flatMap(crp -> crp.corporationTrades.keySet().stream())
				.collect(Collectors.toSet());
		for (Entry<Integer, Eblueprints> e : Eblueprints.LOADER.load().entrySet()) {
			EveType bpType = TypeIndex.getType(e.getValue().blueprintTypeID);
			if (bpType == null) {
				log.warn("skipping null-type bp id=" + e.getValue().blueprintTypeID + ", likely not published");
				continue;
			}
			if (!bpType.published) {
				log.debug("skipping unpublished bp " + bpType.name + "(" + bpType.id + ")");
				continue;
			}
			Blueprint translated = makeBlueprint(e.getValue());
			// don't process a BP that lacks any activity
			if (translated.copying == null || translated.invention == null || translated.manufacturing == null
					|| translated.reaction == null
					|| translated.research_material == null || translated.research_time == null) {
				Set<String> missingActivities = new HashSet<>();
				if (translated.copying == null) {
					missingActivities.add("copying");
				}
				if (translated.invention == null) {
					missingActivities.add("invention");
				}
				if (translated.manufacturing == null) {
					missingActivities.add("manufacturing");
				}
				if (translated.reaction == null) {
					missingActivities.add("reaction");
				}
				if (translated.research_material == null) {
					missingActivities.add("research_material");
				}
				if (translated.research_time == null) {
					missingActivities.add("research_time");
				}
				log.debug(
						"skipping bp " + translated.name() + "(" + translated.id + ")" + " for unresolved activities : "
								+ missingActivities);
				continue;
			}
			translated.seeded = seededItems.contains(translated.id) && !translated.name().endsWith("II Blueprint");
			blueprints.put(e.getValue().blueprintTypeID, translated);
			addUsages(translated, usages);
		}

		for (Entry<Integer, EtypeMaterials> e : EtypeMaterials.load().entrySet()) {
			EveType inputMat = TypeIndex.getType(e.getKey());
			if (inputMat == null) {
				log.warn("can't find type id=" + e.getKey() + " reprocessed into " + e.getValue());
				continue;
			}
			int portionSize = inputMat.portionSize;
			IndustryUsage usage = usages.computeIfAbsent(e.getKey(), _ -> new IndustryUsage());
			for (Material mat : e.getValue().materials) {
				EveType outputmat = TypeIndex.getType(mat.materialTypeID);
				if (outputmat == null) {
					log.error("can't find type id " + mat.materialTypeID + " reprocessed from " + inputMat.name);
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
		bp2.id = bp.blueprintTypeID;
		bp2.maxCopyRuns = bp.maxProductionLimit;
		bp2.copying = convertActivity(bp.activities.copying);
		bp2.invention = convertActivity(bp.activities.invention);
		bp2.manufacturing = convertActivity(bp.activities.manufacturing);
		bp2.research_material = convertActivity(bp.activities.research_material);
		bp2.research_time = convertActivity(bp.activities.research_time);
		bp2.reaction = convertActivity(bp.activities.reaction);
		return bp2;
	}

	/**
	 * try to convert an SDE activity
	 *
	 * @param activity
	 *                 the SDE activity
	 * @return null if a type referred to in the activity was not found.
	 */
	Activity convertActivity(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity activity) {
		AtomicBoolean skip = new AtomicBoolean(false);
		Activity ret = new Activity();
		ret.time = activity.time;
		activity.materials.stream().map(this::convertMaterialReq)
				.peek(o -> {
					if (o == null) {
						skip.set(true);
					}
				})
				.filter(o -> o != null)
				.sorted(Comparator.comparing(m1 -> m1.id))
				.forEach(ret.materials::add);
		activity.products.stream().map(this::convertMaterialProd)
				.peek(o -> {
					if (o == null) {
						skip.set(true);
					}
				})
				.filter(o -> o != null)
				.sorted(Comparator.comparing(m1 -> m1.id))
				.forEach(ret.products::add);
		activity.skills.stream().sorted(Comparator.comparing(s1 -> s1.typeID)).forEach(s -> {
			EveType skill = TypeIndex.getType(s.typeID);
			if (skill == null) {
				log.error("missing skill " + s.typeID);
				skip.set(true);
			} else {
				TypeRef<Skill> ref = new TypeRef<>();
				ref.id = skill.id;
				ret.skills.put(ref, s.level);
			}
		});
		if (skip.get()) {
			return null;
		}
		return ret;
	}

	MaterialReq<?> convertMaterialReq(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material sdeMat) {
		EveType item = TypeIndex.getType(sdeMat.typeID);
		if (item != null) {
			MaterialReq<?> ret = new MaterialReq<>();
			ret.quantity = sdeMat.quantity;
			ret.id = sdeMat.typeID;
			return ret;
		} else {
			log.error("missing type id=" + sdeMat.typeID + " for material conversion");
			return null;
		}
	}

	MaterialProd<?> convertMaterialProd(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material sdeMat) {
		EveType item = TypeIndex.getType(sdeMat.typeID);
		if (item != null) {
			MaterialProd<?> ret = new MaterialProd<>();
			ret.quantity = sdeMat.quantity;
			ret.id = sdeMat.typeID;
			ret.probability = sdeMat.probability;
			return ret;
		} else {
			log.error("missing type id=" + sdeMat.typeID + " for product conversion");
			return null;
		}
	}

	/**
	 * add the usage of a bp into the map of usages : for each activity, add the
	 * bp as using the materials of that activity
	 */
	void addUsages(Blueprint bp, Map<Integer, IndustryUsage> usages) {
		addUsages(bp.id, usages, bp.manufacturing.products, u -> u.productOfManuf);
		addUsages(bp.id, usages, bp.manufacturing.materials, u -> u.materialInManuf);
		addUsages(bp.id, usages, bp.copying.materials, u -> u.materialInCopy);
		addUsages(bp.id, usages, bp.invention.materials, u -> u.materialInInvention);
		addUsages(bp.id, usages, bp.invention.products, u -> u.productOfInvention);
		addUsages(bp.id, usages, bp.research_material.materials, u -> u.materialInME);
		addUsages(bp.id, usages, bp.research_time.materials, u -> u.materialInTE);
		addUsages(bp.id, usages, bp.reaction.materials, u -> u.materialInReaction);
		addUsages(bp.id, usages, bp.reaction.products, u -> u.productOfReaction);
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
