package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;

public interface SkillReqRepository extends JpaRepository<SkillReq, Long> {

	public List<SkillReq> findAllByActivityTypeIdInAndActivityActivityIn(List<Integer> typeIds,
			List<ACTIVITY_TYPE> ats);

	public List<SkillReq> findAllByActivity(BlueprintActivity activity);
}
