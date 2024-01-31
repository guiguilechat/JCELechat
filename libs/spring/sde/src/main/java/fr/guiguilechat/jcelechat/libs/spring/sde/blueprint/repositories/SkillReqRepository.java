package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.SkillReq;

public interface SkillReqRepository extends JpaRepository<SkillReq, Long> {

	public List<SkillReq> findAllByActivityTypeTypeIdInAndActivityActivityIn(List<Integer> typeIds,
			List<ACTIVITY_TYPE> ats);

	public List<SkillReq> findAllByActivity(BlueprintActivity activity);
}
