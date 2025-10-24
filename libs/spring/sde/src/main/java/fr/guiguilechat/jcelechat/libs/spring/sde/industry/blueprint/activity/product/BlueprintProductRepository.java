package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface BlueprintProductRepository extends DeducedEntityRepository<BlueprintProduct> {

	List<BlueprintProduct> findAllByActivityTypeIdAndActivityActivityType(int bpId, ActivityType activityType);

	List<BlueprintProduct> findAllByActivityTypeIdInAndActivityActivityType(Iterable<Integer> bpIds,
			ActivityType activityType);

	List<BlueprintProduct> findAllByActivityActivityTypeIn(Iterable<ActivityType> activities);

	List<BlueprintProduct> findAllByTypeIdInAndActivityActivityTypeIn(Iterable<Integer> productIds,
			Iterable<ActivityType> ats);

}
