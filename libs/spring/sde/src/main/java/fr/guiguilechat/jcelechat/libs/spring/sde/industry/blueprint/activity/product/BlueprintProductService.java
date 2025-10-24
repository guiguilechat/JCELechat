package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class BlueprintProductService extends DeducedEntityService<BlueprintProduct, BlueprintProductRepository> {

	public List<BlueprintProduct> findProducts(Integer productTypeId, ActivityType activity) {
		return repo().findAllByTypeIdAndActivity(productTypeId, activity);
	}

	public List<BlueprintProduct> findProducers(List<Integer> productIds, List<ActivityType> activities) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public List<BlueprintProduct> findProducers(Integer productId, ActivityType act) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public List<BlueprintProduct> findProducts(List<ActivityType> of) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public List<BlueprintProduct> findProducers(Set<Integer> productIds, List<ActivityType> of) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
