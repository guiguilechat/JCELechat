package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class BlueprintMaterialService extends DeducedEntityService<BlueprintMaterial, BlueprintMaterialRepository> {

	public List<BlueprintMaterial> forBPActivity(Iterable<Integer> bpIds, Iterable<ActivityType> activities) {
		return repo().findAllByActivityTypeIdInAndActivityActivityTypeIn(bpIds, activities);
	}

	public List<BlueprintMaterial> forBPActivity(int blueprintId, ActivityType activity) {
		return forBPActivity(List.of(blueprintId), List.of(activity));
	}

	public List<BlueprintMaterial> findUsages(List<Integer> materialIds,
			List<ActivityType> ats) {
		return repo().findAllByTypeIdInAndActivityActivityTypeIn(materialIds, ats);
	}

	public List<BlueprintMaterial> findUsages(Integer materialIds, ActivityType activity) {
		return findUsages(List.of(materialIds), List.of(activity));
	}

	public List<BlueprintMaterial> findUsages(List<Integer> materialIds) {
		return repo().findAllByTypeIdIn(materialIds);
	}

}
