package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ActivityDetails;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ProducingActivityDetails;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BlueprintUpdaterService implements SdeListener {

	private final BlueprintActivityService blueprintActivityService;

	private final MaterialService materialService;

	private final ProductService productService;

	private final SkillReqService skillReqService;

	private final TypeService typeService;

	private boolean sdeFileMissing = true;

	@Override
	public void beforeSdeUpdate() {
		sdeFileMissing = true;
		materialService.clear();
		productService.clear();
		skillReqService.clear();
		blueprintActivityService.clear();
	}

	@Override
	public void onSdeFile(String name, Supplier<InputStream> fileContent) {
		if (name.equals(Eblueprints.SDE_FILE_YAML)) {
			saveBlueprints(fileContent.get());
			return;
		}
	}

	private void saveBlueprints(InputStream is) {
		sdeFileMissing = false;
		List<Eblueprints> blueprints = new ArrayList<>(Eblueprints.LOADER.from(is).values());
		List<Integer> referencedTypeIds = extractTypeReferences(blueprints);
		Map<Integer, Type> typesById = typeService.createIfAbsent(referencedTypeIds);

		// blueprints
		// first create all the activities that exist for each blueprint, store them by
		// bp id, then deduce the material, products, skills entries for those
		// activities

		Map<Integer, BlueprintActivity> copyingByBpId = blueprintActivityService.saveAll(
				blueprints.stream()
				.filter(bp -> bp.activities.copying.active())
				.map(ebp -> BlueprintActivity.of(ebp, ActivityType.copying, typesById.get(ebp.blueprintTypeID)))
				.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> inventionByBpId = blueprintActivityService.saveAll(
				blueprints.stream()
				.filter(bp -> bp.activities.invention.active())
				.map(ebp -> BlueprintActivity.of(ebp, ActivityType.invention, typesById.get(ebp.blueprintTypeID)))
				.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> manufacturingByBpId = blueprintActivityService.saveAll(
				blueprints.stream()
				.filter(bp -> bp.activities.manufacturing.active())
				.map(ebp -> BlueprintActivity.of(ebp, ActivityType.manufacturing,
						typesById.get(ebp.blueprintTypeID)))
				.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> reactionByBpId = blueprintActivityService.saveAll(
				blueprints.stream()
				.filter(bp -> bp.activities.reaction.active())
				.map(ebp -> BlueprintActivity.of(ebp, ActivityType.reaction,
						typesById.get(ebp.blueprintTypeID)))
				.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> researchMatByBpId = blueprintActivityService.saveAll(
				blueprints.stream()
				.filter(bp -> bp.activities.research_material.active())
				.map(ebp -> BlueprintActivity.of(ebp, ActivityType.research_material,
						typesById.get(ebp.blueprintTypeID)))
				.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> researchTimeByBpId = blueprintActivityService.saveAll(
				blueprints.stream()
				.filter(bp -> bp.activities.research_time.active())
				.map(ebp -> BlueprintActivity.of(ebp, ActivityType.research_time,
						typesById.get(ebp.blueprintTypeID)))
				.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getId(), bpa -> bpa));

		List<Material> newMaterials = new ArrayList<>();
		List<Product> newProducts = new ArrayList<>();
		List<SkillReq> newSkills = new ArrayList<>();
		for (Eblueprints ebp : blueprints) {
			addActivityData(copyingByBpId.get(ebp.blueprintTypeID), ebp.activities.copying, typesById, newMaterials,
					newProducts, newSkills);
			addActivityData(inventionByBpId.get(ebp.blueprintTypeID), ebp.activities.invention, typesById,
					newMaterials, newProducts, newSkills);
			addActivityData(manufacturingByBpId.get(ebp.blueprintTypeID), ebp.activities.manufacturing, typesById,
					newMaterials, newProducts, newSkills);
			addActivityData(reactionByBpId.get(ebp.blueprintTypeID), ebp.activities.reaction, typesById,
					newMaterials, newProducts, newSkills);
			addActivityData(researchMatByBpId.get(ebp.blueprintTypeID), ebp.activities.research_material, typesById,
					newMaterials, newProducts, newSkills);
			addActivityData(researchTimeByBpId.get(ebp.blueprintTypeID), ebp.activities.research_time, typesById,
					newMaterials, newProducts, newSkills);
		}
		materialService.saveAll(newMaterials);
		productService.saveAll(newProducts);
		skillReqService.saveAll(newSkills);
		log.info("updated {} blueprints", blueprints.size());
	}

	protected List<Integer> extractTypeReferences(List<Eblueprints> blueprints) {
		return blueprints.stream().flatMap(this::extractTypeReference)
				.distinct().toList();
	}

	protected Stream<Integer> extractTypeReference(Eblueprints bp) {
		return Stream.concat(
				Stream.of(bp.blueprintTypeID),
				Stream.of(bp.activities.copying,
						bp.activities.invention,
						bp.activities.manufacturing,
						bp.activities.reaction,
						bp.activities.research_material,
						bp.activities.research_time)
						.filter(act -> act != null)
						.flatMap(this::extractTypeReference));
	}

	protected Stream<Integer> extractTypeReference(ActivityDetails ad) {
		Stream<Stream<Integer>> partsStream = switch (ad) {
		case ProducingActivityDetails act -> Stream.of(
				act.materials.stream().map(mat -> mat.typeID),
				act.products.stream().map(pr -> pr.typeID),
				act.skills.stream().map(sk -> sk.typeID));
		default -> Stream.of(
				ad.materials.stream().map(mat -> mat.typeID),
				ad.skills.stream().map(sk -> sk.typeID));
		};
		return partsStream.flatMap(s -> s);
	}

	void addActivityData(
			BlueprintActivity bpa,
			ActivityDetails act,
			Map<Integer, Type> typesById,
			List<Material> newMaterials,
			List<Product> newProducts,
			List<SkillReq> newSkills) {
		if (bpa != null) {
			newMaterials.addAll(act.materials.stream()
					.map(m -> Material.of(bpa, typesById.get(m.typeID), m.quantity)).toList());
			if (act instanceof ProducingActivityDetails pad) {
				newProducts.addAll(pad.products.stream()
						.map(p -> Product.of(bpa, typesById.get(p.typeID), p.probability, p.quantity)).toList());
			}
			newSkills.addAll(act.skills.stream()
					.map(s -> SkillReq.of(bpa, typesById.get(s.typeID), s.level)).toList());
		}
	}

	@Override
	public void afterSdeUpdate() {
		if (sdeFileMissing) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file "
					+ Eblueprints.SDE_FILE_YAML);
		}
	}

}
