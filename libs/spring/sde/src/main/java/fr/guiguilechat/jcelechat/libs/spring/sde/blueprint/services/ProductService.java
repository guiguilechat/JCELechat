package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Product> saveAll(Iterable<Product> entities) {
		return repo.saveAll(entities);
	}

	public Product save(Product entity) {
		return repo.save(entity);
	}

	public List<Product> findProducts(List<Integer> bpTypeIds,
			List<ACTIVITY_TYPE> ats) {
		return repo.findAllByActivityTypeTypeIdInAndActivityActivityIn(bpTypeIds, ats);
	}

	@Cacheable("SdeBlueprintFindProducts")
	public List<Product> findProducts(int bpTypeId, ACTIVITY_TYPE ats) {
		return findProducts(List.of(bpTypeId), List.of(ats));
	}

	public List<Product> findProducers(List<Integer> productIds, List<ACTIVITY_TYPE> ats) {
		return repo.findAllByTypeTypeIdInAndActivityActivityIn(productIds, ats);
	}

	@Cacheable("SdeBlueprintFindProducers")
	public List<Product> findProducers(int productId,
			ACTIVITY_TYPE activity) {
		return findProducers(List.of(productId), List.of(activity));
	}

	public static final List<String> CACHE_LIST = List.of("SdeBlueprintFindProducts", "SdeBlueprintFindProducers");

}
