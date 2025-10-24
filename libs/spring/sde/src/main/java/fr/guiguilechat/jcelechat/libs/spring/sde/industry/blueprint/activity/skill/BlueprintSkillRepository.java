package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.skill;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface BlueprintSkillRepository extends DeducedEntityRepository<BlueprintSkill> {

	List<BlueprintSkill> findAllByActivityTypeIdInAndActivityActivityTypeIn(Iterable<Integer> bpTypeIds,
			Iterable<ActivityType> ats);

	List<BlueprintSkill> findAllByActivityActivityTypeIn(Iterable<ActivityType> ats);

}
