package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;

public interface BlueprintActivityRepository extends JpaRepository<BlueprintActivity, Long> {

	public List<BlueprintActivity> findAllByTypeIdInAndActivityIn(List<Integer> typeIds,
			List<ACTIVITY_TYPE> ats);

	public List<BlueprintActivity> findAllByTypeIdIn(List<Integer> typeIds);
}
