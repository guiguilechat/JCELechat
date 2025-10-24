package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface BlueprintActivityRepository extends DeducedEntityRepository<BlueprintActivity> {

	List<BlueprintActivity> findAllByTypeIdInAndActivityTypeIn(List<Integer> typeIds, List<ActivityType> ats);

	List<BlueprintActivity> findAllByTypeIdIn(List<Integer> typeIds);

}
