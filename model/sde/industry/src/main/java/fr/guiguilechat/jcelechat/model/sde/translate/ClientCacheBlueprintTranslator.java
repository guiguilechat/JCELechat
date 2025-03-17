package fr.guiguilechat.jcelechat.model.sde.translate;

import java.sql.SQLException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.structure.common.type.ConsumedMaterial;
import fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata.Eblueprints;
import fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata.Eblueprints.Activities.Activity.Product;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.Activity;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials.Material;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ClientCacheBlueprintTranslator {

	private final ClientCache clientCache;

	List<KeyValTime<Eblueprints>> listBlueprints()
			throws JsonMappingException, JsonProcessingException, SQLException {
		return Eblueprints.getLoader().load(clientCache);
	}

	public void translateBlueprints(ClientCache cc, LinkedHashMap<Integer, Blueprint> blueprints,
			LinkedHashMap<Integer, IndustryUsage> usages)
			throws JsonMappingException, JsonProcessingException, SQLException {
		// set of type ids that are seeded by NPCs
		Set<Integer> seededItems = EnpcCorporations.load().values().stream()
				.flatMap(crp -> crp.corporationTrades.keySet().stream())
				.collect(Collectors.toSet());
		for (KeyValTime<Eblueprints> e : listBlueprints()) {
			int bpId = e.getVal().blueprintTypeId;
			EveType bpType = TypeIndex.getType(bpId);
			if (bpType == null) {
				log.warn("skipping null-type bp id=" + bpId + ", likely not published ");
				continue;
			}
			if (!bpType.published) {
				log.debug("skipping unpublished bp " + bpType.name + "(" + bpId + ")");
				continue;
			}
			Blueprint translated = makeBlueprint(e.getVal());
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
			blueprints.put(bpId, translated);
			addUsages(translated, usages);
		}

		for (Entry<Integer, EtypeMaterials> e : EtypeMaterials.load().entrySet()) {
			EveType inputMat = TypeIndex.getType(e.getKey());
			if (inputMat == null) {
				log.warn("can't find type id=" + e.getKey() + " reprocessed into " + e.getValue());
				continue;
			}
			int portionSize = inputMat.portionSize;
			IndustryUsage usage = usages.computeIfAbsent(e.getKey(), i -> new IndustryUsage());
			for (Material mat : e.getValue().materials) {
				EveType outputmat = TypeIndex.getType(mat.materialTypeID);
				if (outputmat == null) {
					log.error("can't find type id " + mat.materialTypeID + " reprocessed from " + inputMat.name);
					continue;
				}
				usage.reprocessInto.put(mat.materialTypeID, 1.0 * mat.quantity / portionSize);
				usages.computeIfAbsent(mat.materialTypeID, i -> new IndustryUsage()).reprocessedFrom
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
	 * @param copying
	 *                the SDE activity
	 * @return null if a type referred to in the activity was not found.
	 */
	Activity convertActivity(
			fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata.Eblueprints.Activities.Activity copying) {
		AtomicBoolean skip = new AtomicBoolean(false);
		Activity ret = new Activity();
		ret.time = copying.time;
		copying.materials.stream().map(this::convertMaterialReq)
				.peek(o -> {
					if (o == null) {
						skip.set(true);
					}
				})
				.filter(o -> o != null)
				.sorted(Comparator.comparing(m1 -> m1.id))
				.forEach(ret.materials::add);
		copying.products.stream().map(this::convertMaterialProd)
				.peek(o -> {
					if (o == null) {
						skip.set(true);
					}
				})
				.filter(o -> o != null)
				.sorted(Comparator.comparing(m1 -> m1.id))
				.forEach(ret.products::add);
		copying.skills.stream().sorted(Comparator.comparing(s1 -> s1.typeId)).forEach(s -> {
			EveType skill = TypeIndex.getType(s.typeId);
			if (skill == null) {
				log.error("missing skill " + s.typeId);
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

	MaterialReq<?> convertMaterialReq(ConsumedMaterial sdeMat) {
		EveType item = TypeIndex.getType(sdeMat.typeId);
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
		EveType item = TypeIndex.getType(sdeMat.typeId);
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
				IndustryUsage u = usages.computeIfAbsent(m.id, i -> new IndustryUsage());
				categorizer.apply(u).add(bpoID);
			}
		}
	}

}
