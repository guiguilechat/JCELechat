package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface BlueprintProductRepository extends DeducedEntityRepository<BlueprintProduct> {

	List<BlueprintProduct> findAllByTypeIdAndActivity(int typeId, ActivityType activity);

}
