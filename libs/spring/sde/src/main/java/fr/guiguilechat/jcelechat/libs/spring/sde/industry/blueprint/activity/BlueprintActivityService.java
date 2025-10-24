package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class BlueprintActivityService extends DeducedEntityService<BlueprintActivity, BlueprintActivityRepository> {

	public List<BlueprintActivity> forBPActivity(Integer id, ActivityType activityType) {
		return forBPActivity(List.of(id), List.of(activityType));
	}

	public List<BlueprintActivity> forBPActivity(List<Integer> ids, List<ActivityType> activityTypes) {
		return repo().findAllByTypeIdInAndActivityTypeIn(ids, activityTypes);
	}

	public List<BlueprintActivity> forType(int typeId) {
		return repo().findAllByTypeIdIn(List.of(typeId));
	}

}
