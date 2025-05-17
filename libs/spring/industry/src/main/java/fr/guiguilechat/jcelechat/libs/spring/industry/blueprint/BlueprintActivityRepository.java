package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ActivityType;

public interface BlueprintActivityRepository extends JpaRepository<BlueprintActivity, Long> {

	List<BlueprintActivity> findAllByTypeIdInAndActivityIn(List<Integer> typeIds,
			List<ActivityType> ats);

	List<BlueprintActivity> findAllByTypeIdIn(List<Integer> typeIds);
}
