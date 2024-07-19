package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BlueprintUpdaterService implements SdeUpdateListener {

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

	static final Pattern ENTRYNAME_BLUEPRINTS_PATTERN = Pattern.compile(
	    "fsd/blueprints\\.yaml");

	@Override
	public void onSdeFile(String name, Supplier<InputStream> fileContent) {
		if (ENTRYNAME_BLUEPRINTS_PATTERN.matcher(name).matches()) {
			saveBlueprints(fileContent.get());
			return;
		}
	}

	private void saveBlueprints(InputStream is) {
		sdeFileMissing = false;
		List<Eblueprints> blueprints = new ArrayList<>(Eblueprints.from(is).values());
		Map<Integer, Type> typesById = typeService.allById();

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

	void addActivityData(
	    BlueprintActivity bpa,
	    Activity act,
	    Map<Integer, Type> typesById,
	    List<Material> newMaterials,
	    List<Product> newProducts,
	    List<SkillReq> newSkills) {
		if (bpa != null) {
			newMaterials.addAll(act.materials.stream()
			    .map(m -> Material.of(bpa, typesById.get(m.typeID), m.quantity)).toList());
			newProducts.addAll(act.products.stream()
			    .map(p -> Product.of(bpa, typesById.get(p.typeID), p.probability, p.quantity)).toList());
			newSkills.addAll(act.skills.stream()
			    .map(s -> SkillReq.of(bpa, typesById.get(s.typeID), s.level)).toList());
		}
	}

	@Override
	public void afterSdeUpdate() {
		if (sdeFileMissing) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file for matcher "
			    + ENTRYNAME_BLUEPRINTS_PATTERN);
		}
	}

}
