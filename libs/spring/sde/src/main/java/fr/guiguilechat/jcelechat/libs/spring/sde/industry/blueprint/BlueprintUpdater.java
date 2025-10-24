package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ActivityDetails;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ProducingActivityDetails;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product.BlueprintProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product.BlueprintProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.skill.BlueprintSkill;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.skill.BlueprintSkillService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.industry.blueprint")
public class BlueprintUpdater extends SdeEntityUpdater<Blueprint, BlueprintService, Eblueprints> {

	public BlueprintUpdater() {
		super(Eblueprints.SDE_FILE_YAML, Eblueprints.LOADER);
	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private BlueprintActivityService blueprintActivityService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private BlueprintMaterialService blueprintMaterialService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private BlueprintProductService blueprintProductService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private BlueprintSkillService blueprintSkillService;

	@Override
	public void beforeSdeUpdate() {
		super.beforeSdeUpdate();
		if (skip) {
			return;
		}
		blueprintActivityService().delete();
		blueprintMaterialService().delete();
		blueprintProductService().delete();
		blueprintSkillService().delete();
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, Eblueprints> sources) {
		var storedEntities = new HashMap<>(service().allById());
		List<BlueprintActivity> activities = new ArrayList<>();
		List<BlueprintMaterial> materials = new ArrayList<>();
		List<BlueprintProduct> products = new ArrayList<>();
		List<BlueprintSkill> skills = new ArrayList<>();
		for (var e : sources.entrySet()) {
			int typeId = e.getKey();
			Eblueprints bp = e.getValue();
			var stored = storedEntities.computeIfAbsent(typeId, service()::create);
			stored.update(bp);
			BPActivities acts = bp.activities;
			if (acts != null) {
				Map<ActivityType, ActivityDetails> m = Map.of(
						ActivityType.copying, acts.copying,
						ActivityType.invention, acts.invention,
						ActivityType.manufacturing, acts.manufacturing,
						ActivityType.reaction, acts.reaction,
						ActivityType.research_material, acts.research_material,
						ActivityType.research_time, acts.research_time
				);
				for (Entry<ActivityType, ActivityDetails> e2 : m.entrySet()) {
					if (e2.getValue() != null) {
						addActivityDetails(typeId, e2.getValue(), e2.getKey(), activities, materials, products, skills);
					}
				}
			}
		}
		service().saveAll(storedEntities.values());
		blueprintActivityService.saveAll(activities);
		blueprintMaterialService.saveAll(materials);
		blueprintProductService.saveAll(products);
		blueprintSkillService.saveAll(skills);
	}

	protected void addActivityDetails(int bpId, ActivityDetails act, ActivityType activityType,
			List<BlueprintActivity> activities,
			List<BlueprintMaterial> materials,
			List<BlueprintProduct> products,
			List<BlueprintSkill> skills) {
		BlueprintActivity bpa = BlueprintActivity.of(act, bpId, activityType);
		activities.add(bpa);
		if (act.materials != null) {
			for (var e : act.materials) {
				materials.add(BlueprintMaterial.of(e, bpa));
			}
		}
		if (act instanceof ProducingActivityDetails pad) {
			if (pad.products != null) {
				for (var e : pad.products) {
					products.add(BlueprintProduct.of(e, bpa));
				}
			}
		}
		if (act.skills != null) {
			for (var e : act.skills) {
				skills.add(BlueprintSkill.of(e, bpa));
			}
		}
	}

}
