package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class BlueprintActivityService extends DeducedEntityService<BlueprintActivity, BlueprintActivityRepository> {

	public List<BlueprintActivity> forBPActivity(Integer id, ActivityType researchTime) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public List<BlueprintActivity> forBPActivity(List<Integer> ids, List<ActivityType> activities) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
