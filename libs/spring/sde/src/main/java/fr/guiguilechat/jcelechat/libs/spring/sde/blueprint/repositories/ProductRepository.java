package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findAllByActivityTypeTypeIdInAndActivityActivityIn(List<Integer> blueprintIds,
			List<ACTIVITY_TYPE> ats);

	public List<Product> findAllByActivity(BlueprintActivity activity);

	public List<Product> findAllByTypeTypeIdInAndActivityActivityIn(List<Integer> productIds,
			List<ACTIVITY_TYPE> ats);

}
