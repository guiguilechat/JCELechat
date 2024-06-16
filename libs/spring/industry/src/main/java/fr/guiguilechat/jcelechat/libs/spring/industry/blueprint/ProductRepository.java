package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findAllByActivityTypeTypeIdInAndActivityActivityIn(List<Integer> blueprintIds,
			List<ACTIVITY_TYPE> ats);

	public List<Product> findAllByActivity(BlueprintActivity activity);

	public List<Product> findAllByTypeTypeIdInAndActivityActivityIn(List<Integer> productIds,
			List<ACTIVITY_TYPE> ats);

}
