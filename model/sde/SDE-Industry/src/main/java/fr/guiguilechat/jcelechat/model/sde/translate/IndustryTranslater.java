package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.Activity;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialProd;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.industry.InventionDecryptor;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EcrpNPCCorporationTrades;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials.Material;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EcategoryIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EgroupIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;
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

		LinkedHashMap<String, Blueprint> blueprints = new LinkedHashMap<>();
		LinkedHashMap<String, InventionDecryptor> decryptors = new LinkedHashMap<>();
		LinkedHashMap<String, IndustryUsage> usages = new LinkedHashMap<>();

		translate(blueprints, decryptors, usages);

		// sort

		Stream.of(blueprints, decryptors).forEach(m -> {
			ArrayList<Entry<String, ? extends Object>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
			m.clear();
			for (Entry<String, ? extends Object> e : list) {
				((Map<String, Object>) m).put(e.getKey(), e.getValue());
			}
		});

		// save

		Blueprint.export(blueprints, folderOut);
		InventionDecryptor.export(decryptors, folderOut);
		IndustryUsage.export(usages, folderOut);

		System.err.println("exported industry in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");

	}

	private static void translate(LinkedHashMap<String, Blueprint> blueprints,
			LinkedHashMap<String, InventionDecryptor> decryptors, LinkedHashMap<String, IndustryUsage> usages) {
		LinkedHashMap<Integer, EtypeIDs> types = EtypeIDs.load();
		Set<Integer> seededItems = EcrpNPCCorporationTrades.load().stream().map(t -> t.typeID).collect(Collectors.toSet());
		for (Entry<Integer, Eblueprints> e : Eblueprints.load().entrySet()) {
			EtypeIDs type = types.get(e.getValue().blueprintTypeID);
			if (type != null) {
				if (type.published) {
					Blueprint bp2 = makeBlueprint(e.getValue(), types);
					bp2.name = type.enName();
					bp2.seeded = seededItems.contains(bp2.id);
					blueprints.put(type.enName(), bp2);
					addUsages(bp2, usages);
				} else {
					logger.info("skipping bp for unpublished " + type.enName());
				}
			} else {
				logger.warn("can't find type for blueprint id " + e.getValue().blueprintTypeID);
			}
		}

		for (Entry<Integer, EtypeMaterials> e : EtypeMaterials.load().entrySet()) {
			EtypeIDs inputMat = types.get(e.getKey());
			if (inputMat == null) {
				System.err.println("can't find item " + e.getKey() + " that reprocess in " + e.getValue());
				continue;
			}
			int portionSize = inputMat.portionSize;
			IndustryUsage usage = usages.get(inputMat.enName());
			if (usage == null) {
				usage = new IndustryUsage();
				usages.put(inputMat.enName(), usage);
			}
			for (Material mat : e.getValue().materials) {
				EtypeIDs outputmat = types.get(mat.materialTypeID);
				if (outputmat != null) {
					usage.reprocess.put(outputmat.enName(), 1.0 * mat.quantity / portionSize);
				} else {
					System.err.println("can't find type id " + mat.materialTypeID + " reprocessed from " + inputMat.enName());
				}
			}
		}

		for (Entry<String, GenericDecryptor> e : GenericDecryptor.METAGROUP.load().entrySet()) {
			decryptors.put(e.getKey(), convertDecryptor(e.getValue()));
		}

		InventionDecryptor nullDecryptor = new InventionDecryptor();
		nullDecryptor.name = "no decryptor";
		decryptors.put(nullDecryptor.name, nullDecryptor);

		// sort the usages by item name
		ArrayList<Entry<String, IndustryUsage>> l = new ArrayList<>(usages.entrySet());
		Collections.sort(l, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		usages.clear();
		for (Entry<String, IndustryUsage> e : l) {
			usages.put(e.getKey(), e.getValue());
		}
	}

	private static Blueprint makeBlueprint(Eblueprints bp, LinkedHashMap<Integer, EtypeIDs> types) {
		Blueprint bp2 = new Blueprint();
		bp2.id = bp.blueprintTypeID;
		bp2.maxCopyRuns = bp.maxProductionLimit;
		bp2.copying = convertEblueprint(bp.activities.copying, types);
		bp2.invention = convertEblueprint(bp.activities.invention, types);
		bp2.manufacturing = convertEblueprint(bp.activities.manufacturing, types);
		bp2.research_material = convertEblueprint(bp.activities.research_material, types);
		bp2.research_time = convertEblueprint(bp.activities.research_time, types);
		bp2.reaction = convertEblueprint(bp.activities.reaction, types);
		return bp2;
	}

	public static Activity convertEblueprint(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity activity,
			LinkedHashMap<Integer, EtypeIDs> types) {
		Activity ret = new Activity();
		ret.time = activity.time;
		activity.materials.stream().map(m -> convertMaterialReq(m, types)).forEach(ret.materials::add);
		activity.products.stream().map(p -> convertMaterialProd(p, types)).forEach(ret.products::add);
		activity.skills.stream().forEach(s -> ret.skills.put(EtypeIDs.getName(s.typeID), s.level));
		return ret;
	}

	public static MaterialReq convertMaterialReq(fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material sdeMat,
			LinkedHashMap<Integer, EtypeIDs> types) {
		EtypeIDs item = types.get(sdeMat.typeID);
		if (item != null) {
			MaterialReq ret = new MaterialReq();
			ret.quantity = sdeMat.quantity;
			ret.name = item.enName();
			ret.id = sdeMat.typeID;
			EgroupIDs group = EgroupIDs.load().get(item.groupID);
			ret.group = group.enName();
			EcategoryIDs cat = EcategoryIDs.load().get(group.categoryID);
			ret.category = cat.enName();
			return ret;
		} else {
			return null;
		}
	}

	public static MaterialProd convertMaterialProd(
			fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material sdeMat,
			LinkedHashMap<Integer, EtypeIDs> types) {
		EtypeIDs item = types.get(sdeMat.typeID);
		if (item != null) {
			MaterialProd ret = new MaterialProd();
			ret.quantity = sdeMat.quantity;
			ret.name = item.enName();
			ret.id = sdeMat.typeID;
			ret.probability = sdeMat.probability;
			EgroupIDs group = EgroupIDs.load().get(item.groupID);
			ret.group = group.enName();
			EcategoryIDs cat = EcategoryIDs.load().get(group.categoryID);
			ret.category = cat.enName();
			return ret;
		} else {
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

	public static void addUsages(Blueprint bp, Map<String, IndustryUsage> usages) {
		addUsages(bp.name, usages, bp.manufacturing.products, u -> u.productManuf);
		addUsages(bp.name, usages, bp.manufacturing.materials, u -> u.materialManuf);
		addUsages(bp.name, usages, bp.copying.materials, u -> u.materialCopy);
		addUsages(bp.name, usages, bp.invention.materials, u -> u.materialInvention);
		addUsages(bp.name, usages, bp.invention.products, u -> u.productInvention);
		addUsages(bp.name, usages, bp.research_material.materials, u -> u.materialME);
		addUsages(bp.name, usages, bp.research_time.materials, u -> u.materialTE);
	}

	protected static void addUsages(String name, Map<String, IndustryUsage> usages, List<? extends MaterialReq> materials,
			Function<IndustryUsage, Set<String>> categorizer) {
		for (MaterialReq m : materials) {
			IndustryUsage u = usages.get(m.name);
			if (u == null) {
				u = new IndustryUsage();
				usages.put(m.name, u);
			}
			categorizer.apply(u).add(name);
		}
	}
}
