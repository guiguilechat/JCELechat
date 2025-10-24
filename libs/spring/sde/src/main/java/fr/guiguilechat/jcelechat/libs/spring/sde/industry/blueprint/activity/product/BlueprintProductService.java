package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class BlueprintProductService extends DeducedEntityService<BlueprintProduct, BlueprintProductRepository> {

	public List<BlueprintProduct> findProducts(Integer bpId, ActivityType activity) {
		return repo().findAllByActivityTypeIdAndActivityActivityType(bpId, activity);
	}

	public List<BlueprintProduct> findProducts(Iterable<Integer> bpIds, ActivityType activity) {
		return repo().findAllByActivityTypeIdInAndActivityActivityType(bpIds, activity);
	}

	public List<BlueprintProduct> findProducts(Iterable<ActivityType> activities) {
		return repo().findAllByActivityActivityTypeIn(activities);
	}

	public List<BlueprintProduct> findProducers(Iterable<Integer> productIds, Iterable<ActivityType> activities) {
		return repo().findAllByTypeIdInAndActivityActivityTypeIn(productIds, activities);
	}

	public List<BlueprintProduct> findProducers(Integer productId, ActivityType act) {
		return findProducers(List.of(productId), List.of(act));
	}

}
