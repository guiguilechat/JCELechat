package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

	public List<Material> findAllByActivityTypeTypeIdInAndActivityActivityIn(List<Integer> typeIds,
			List<ACTIVITY_TYPE> ats);

	public List<Material> findAllByActivity(BlueprintActivity activity);

	public List<Material> findAllByTypeTypeIdInAndActivityActivityIn(List<Integer> typeIds,
			List<ACTIVITY_TYPE> ats);

}
