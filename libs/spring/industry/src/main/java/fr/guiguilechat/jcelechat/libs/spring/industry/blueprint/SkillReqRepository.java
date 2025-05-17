package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ActivityType;

public interface SkillReqRepository extends JpaRepository<SkillReq, Long> {

	List<SkillReq> findAllByActivityTypeIdInAndActivityActivityIn(List<Integer> typeIds,
			List<ActivityType> ats);

	List<SkillReq> findAllByActivity(BlueprintActivity activity);
}
