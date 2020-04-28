package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.Activity;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.industry.InventionDecryptor;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EcrpNPCCorporationTrades;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials.Material;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.GenericDecryptor;

public class IndustryTranslater {

	private static final Logger logger = LoggerFactory.getLogger(IndustryTranslater.class);

	/**
	 *
	 * @param args
	 *          should be [database destination root], typically
	 *          src/generated/resources/
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length == 0 ? "src/generated/resources/" : args[0]);
		FileTools.delDir(folderOut);
		folderOut.mkdirs();

		LinkedHashMap<Integer, Blueprint> blueprints = new LinkedHashMap<>();
		LinkedHashMap<Integer, InventionDecryptor> decryptors = new LinkedHashMap<>();
		LinkedHashMap<Integer, IndustryUsage> usages = new LinkedHashMap<>();

		translateBlueprints(blueprints, decryptors, usages);
		translateCompression(usages);

		// sort decryptors

		Stream.of(blueprints, decryptors).forEach(m -> {
			ArrayList<Entry<Integer, ? extends Object>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
			m.clear();
			for (Entry<Integer, ? extends Object> e : list) {
				((Map<Integer, Object>) m).put(e.getKey(), e.getValue());
			}
		});

		// save

		Blueprint.export(blueprints, folderOut);
		InventionDecryptor.export(decryptors, folderOut);
		IndustryUsage.export(usages, folderOut);

		System.err.println("exported industry in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");

	}

	private static void translateBlueprints(LinkedHashMap<Integer, Blueprint> blueprints,
			LinkedHashMap<Integer, InventionDecryptor> decryptors, LinkedHashMap<Integer, IndustryUsage> usages) {
		// set of type ids that are seeded by NPCs
		Set<Integer> seededItems = EcrpNPCCorporationTrades.load().stream().map(t -> t.typeID).collect(Collectors.toSet());
		for (Entry<Integer, Eblueprints> e : Eblueprints.load().entrySet()) {
			EveType type = TypeIndex.getType(e.getValue().blueprintTypeID);
			if (type != null) {
				if (type.published) {
					Blueprint bp2 = makeBlueprint(e.getValue());
					bp2.name = type.name;
					if (bp2.copying == null || bp2.invention == null || bp2.manufacturing == null || bp2.reaction == null
							|| bp2.research_material == null || bp2.research_time == null) {
						Set<String> missingActivities = new HashSet<>();
						if (bp2.copying == null) {
							missingActivities.add("copying");
						}
						if (bp2.invention == null) {
							missingActivities.add("invention");
						}
						if (bp2.manufacturing == null) {
							missingActivities.add("manufacturing");
						}
						if (bp2.reaction == null) {
							missingActivities.add("reaction");
						}
						if (bp2.research_material == null) {
							missingActivities.add("research_material");
						}
						if (bp2.research_time == null) {
							missingActivities.add("research_time");
						}
						logger.debug(
								"skipping bp " + bp2.name + "(" + bp2.id + ")" + " for unresolved activities : " + missingActivities);
						continue;
					}
					bp2.seeded = seededItems.contains(bp2.id);
					blueprints.put(e.getValue().blueprintTypeID, bp2);
					addUsages(bp2, usages);
				} else {
					logger.debug("skipping bp for unpublished " + type.name);
				}
			} else {
				logger.debug("skipping unpublished bp id=" + e.getValue().blueprintTypeID);
			}
		}

		for (Entry<Integer, EtypeMaterials> e : EtypeMaterials.load().entrySet()) {
			EveType inputMat = TypeIndex.getType(e.getKey());
			if (inputMat == null) {
				logger.debug("can't find type id=" + e.getKey() + " that reprocess in " + e.getValue());
				continue;
			}
			int portionSize = inputMat.portionSize;
			IndustryUsage usage = usages.computeIfAbsent(e.getKey(), i -> new IndustryUsage());
			for (Material mat : e.getValue().materials) {
				EveType outputmat = TypeIndex.getType(mat.materialTypeID);
				if (outputmat != null) {
					usage.reprocessInto.put(e.getKey(), 1.0 * mat.quantity / portionSize);
				} else {
					logger.debug("can't find type id " + mat.materialTypeID + " reprocessed from " + inputMat.name);
				}
			}
		}

		for (Entry<String, GenericDecryptor> e : GenericDecryptor.METAGROUP.load().entrySet()) {
			decryptors.put(e.getValue().id, convertDecryptor(e.getValue()));
		}

		InventionDecryptor nullDecryptor = new InventionDecryptor();
		nullDecryptor.name = "no decryptor";
		decryptors.put(0, nullDecryptor);

		// sort the usages by item name
		ArrayList<Entry<Integer, IndustryUsage>> l = new ArrayList<>(usages.entrySet());
		Collections.sort(l, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		usages.clear();
		for (Entry<Integer, IndustryUsage> e : l) {
			usages.put(e.getKey(), e.getValue());
		}
	}

	private static Blueprint makeBlueprint(Eblueprints bp) {
		Blueprint bp2 = new Blueprint();
		bp2.id = bp.blueprintTypeID;
		bp2.maxCopyRuns = bp.maxProductionLimit;
		bp2.copying = convertEblueprint(bp.activities.copying);
		bp2.invention = convertEblueprint(bp.activities.invention);
		bp2.manufacturing = convertEblueprint(bp.activities.manufacturing);
		bp2.research_material = convertEblueprint(bp.activities.research_material);
		bp2.research_time = convertEblueprint(bp.activities.research_time);
		bp2.reaction = convertEblueprint(bp.activities.reaction);
		return bp2;
	}

	public static Activity convertEblueprint(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity activity) {
		boolean[] skip = new boolean[] { false };
		Activity ret = new Activity();
		ret.time = activity.time;
		activity.materials.stream().map(m -> convertMaterialReq(m)).peek(o -> skip[0] = skip[0] || o == null)
		.forEach(ret.materials::add);
		activity.products.stream().map(p -> convertMaterialProd(p)).peek(o -> skip[0] = skip[0] || o == null)
		.forEach(ret.products::add);
		activity.skills.stream().forEach(s -> {
			EveType skill = TypeIndex.getType(s.typeID);
			if (skill == null) {
				logger.debug("missing skill " + s.typeID);
				skip[0] = true;
			} else {
				ret.skills.put(skill.name, s.level);
			}
		});
		if (skip[0]) {
			return null;
		}
		return ret;
	}

	public static MaterialReq convertMaterialReq(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material sdeMat) {
		EveType item = TypeIndex.getType(sdeMat.typeID);
		if (item != null) {
			MaterialReq ret = new MaterialReq();
			ret.quantity = sdeMat.quantity;
			ret.id = sdeMat.typeID;
			ret.group = item.getGroup().getName();
			ret.category = item.getCategory().getName();
			return ret;
		} else {
			logger.debug("missing type id=" + sdeMat.typeID);
			return null;
		}
	}

	public static MaterialProd convertMaterialProd(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material sdeMat) {
		EveType item = TypeIndex.getType(sdeMat.typeID);
		if (item != null) {
			MaterialProd ret = new MaterialProd();
			ret.quantity = sdeMat.quantity;
			ret.id = sdeMat.typeID;
			ret.probability = sdeMat.probability;
			ret.group = item.getGroup().getName();
			ret.category = item.getCategory().getName();
			return ret;
		} else {
			logger.debug("missing type id=" + sdeMat.typeID);
			return null;
		}
	}

	public static InventionDecryptor convertDecryptor(GenericDecryptor dec) {
		InventionDecryptor ret = new InventionDecryptor();
		ret.me = (int) dec.InventionMEModifier;
		ret.te = (int) dec.InventionTEModifier;
		ret.maxrun = (int) dec.InventionMaxRunModifier;
		ret.id = dec.id;
		ret.name = dec.name;
		ret.probmult = dec.InventionPropabilityMultiplier;
		return ret;
	}

	/**
	 * add the usage of a bp into the map of usages : for each activity, add the
	 * bp as using the materials of that activity
	 */
	public static void addUsages(Blueprint bp, Map<Integer, IndustryUsage> usages) {
		addUsages(bp.id, usages, bp.manufacturing.products, u -> u.productOfManuf);
		addUsages(bp.id, usages, bp.manufacturing.materials, u -> u.materialInManuf);
		addUsages(bp.id, usages, bp.copying.materials, u -> u.materialInCopy);
		addUsages(bp.id, usages, bp.invention.materials, u -> u.materialInInvention);
		addUsages(bp.id, usages, bp.invention.products, u -> u.productOfInvention);
		addUsages(bp.id, usages, bp.research_material.materials, u -> u.materialInME);
		addUsages(bp.id, usages, bp.research_time.materials, u -> u.materialInTE);
	}

	protected static void addUsages(Integer bpoID, Map<Integer, IndustryUsage> usages,
			List<? extends MaterialReq> materials, Function<IndustryUsage, Set<Integer>> categorizer) {
		if (materials == null || materials.isEmpty()) {
		} else {
			for (MaterialReq m : materials) {
				if (m == null) {
					logger.debug("null material in list of bp id=" + bpoID + " : " + materials);
				}
				IndustryUsage u = usages.computeIfAbsent(m.id, i -> new IndustryUsage());
				categorizer.apply(u).add(bpoID);
			}
		}
	}

	private static void translateCompression(LinkedHashMap<Integer, IndustryUsage> usages) {
		for (Asteroid compressed : Asteroid.METACAT.load().values()) {
			try {
				Field field = compressed.getClass().getField("CompressionTypeID");
				Integer compressIntoId = (Integer) field.get(compressed);
				if (compressIntoId != null && compressIntoId != 0) {
					EveType compressInto = TypeIndex.getType(compressIntoId);
					if (compressInto == null) {
						logger.debug("can't find asteroid from id " + compressIntoId);
					} else {
						usages.computeIfAbsent(compressed.id, o -> new IndustryUsage()).compressTo = compressIntoId;
						usages.computeIfAbsent(compressIntoId, o -> new IndustryUsage()).compressFrom = compressed.id;
					}
				}
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// nothing, no such a field
			}
		}
		// TODO Auto-generated method stub

	}
}
