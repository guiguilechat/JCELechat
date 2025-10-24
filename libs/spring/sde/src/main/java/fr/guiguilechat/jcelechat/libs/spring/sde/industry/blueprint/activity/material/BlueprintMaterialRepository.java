package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface BlueprintMaterialRepository extends DeducedEntityRepository<BlueprintMaterial> {

	@EntityGraph("activity")
	List<BlueprintMaterial> findAllByActivityTypeIdInAndActivityActivityTypeIn(Iterable<Integer> blueprintIds,
			Iterable<ActivityType> ats);

	@EntityGraph("activity")
	List<BlueprintMaterial> findAllByTypeIdInAndActivityActivityTypeIn(List<Integer> materialTypeIds,
			List<ActivityType> activities);

	@EntityGraph("activity")
	List<BlueprintMaterial> findAllByTypeIdIn(List<Integer> materialTypeIds);

}
