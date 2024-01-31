package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;

public interface BlueprintActivityRepository extends JpaRepository<BlueprintActivity, Long> {

	@Query("""
select
	activity
from
	SdeBlueprintProduct
where
	type.typeId in :typeIds
""")

	public List<BlueprintActivity> producing(List<Integer> typeIds);

	public List<BlueprintActivity> findAllByTypeTypeIdInAndActivityIn(List<Integer> typeIds,
			List<ACTIVITY_TYPE> ats);
}
