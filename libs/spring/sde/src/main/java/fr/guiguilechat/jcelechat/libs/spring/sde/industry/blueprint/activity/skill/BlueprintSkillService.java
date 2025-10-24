package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.skill;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class BlueprintSkillService extends DeducedEntityService<BlueprintSkill, BlueprintSkillRepository> {

	public List<BlueprintSkill> requiredFor(Iterable<Integer> bpTypeIds,
			Iterable<ActivityType> ats) {
		return repo().findAllByActivityTypeIdInAndActivityActivityTypeIn(bpTypeIds, ats);
	}

	public List<BlueprintSkill> requiredFor(Iterable<ActivityType> ats) {
		return repo().findAllByActivityActivityTypeIn(ats);
	}

}
