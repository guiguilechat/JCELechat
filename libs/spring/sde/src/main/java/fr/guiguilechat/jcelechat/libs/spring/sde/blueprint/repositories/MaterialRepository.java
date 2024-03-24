package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;

public interface MaterialRepository extends JpaRepository<Material, Long> {

	public List<Material> findAllByActivityTypeTypeIdInAndActivityActivityIn(List<Integer> typeIds,
			List<ACTIVITY_TYPE> ats);

	public List<Material> findAllByActivity(BlueprintActivity activity);

	@Query("""
select
	distinct(mat.type)
from
	SdeBlueprintMaterial mat
where
	mat.activity.activity=:activity_type
	and mat.type.group.category.categoryId=:category_id
""")
	public Set<Type> allActivityMaterialsInCategory(ACTIVITY_TYPE activity_type, int category_id);

	public List<Material> findAllByTypeTypeIdInAndActivityActivityIn(List<Integer> typeIds,
			List<ACTIVITY_TYPE> ats);

}
