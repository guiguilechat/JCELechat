package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByActivityTypeIdInAndActivityActivityIn(List<Integer> blueprintIds,
			List<ActivityType> ats);

	List<Product> findAllByActivity(BlueprintActivity activity);

	@EntityGraph("activity.type,type")
	List<Product> findAllByActivityActivityIn(Iterable<ActivityType> activities);

	@EntityGraph("activity.type,type")
	List<Product> findAllByTypeIdInAndActivityActivityIn(Iterable<Integer> productIds,
			Iterable<ActivityType> ats);

	@Query("""
select
	type,
	activity.type
from
	SdeBlueprintProduct
where
	id in :rowIds
""") List<Type[]> loadTypes(Iterable<Long> rowIds);

}
