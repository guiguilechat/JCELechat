package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;

public interface MaterialRepository extends JpaRepository<Material, Long> {

	@EntityGraph("activity.type,type")
	List<Material> findAllByActivityTypeIdInAndActivityActivityIn(Iterable<Integer> typeIds,
	    Iterable<ACTIVITY_TYPE> ats);

	@EntityGraph("activity.type,type")
	List<Material> findAllByActivity(BlueprintActivity activity);

	@Query("""
select
	distinct(mat.type)
from
	SdeBlueprintMaterial mat
where
	mat.activity.activity=:activity_type
	and mat.type.group.category.id=:category_id
""") Set<Type> allActivityMaterialsInCategory(ACTIVITY_TYPE activity_type, int category_id);

	List<Material> findAllByTypeIdInAndActivityActivityIn(List<Integer> typeIds,
	    List<ACTIVITY_TYPE> ats);

}
