package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductService implements SdeListener {

	final private ProductRepository repo;

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
			List<ActivityType> ats) {
		return repo.findAllByActivityTypeIdInAndActivityActivityIn(bpTypeIds, ats);
	}

	public List<Product> findProducts(Iterable<ActivityType> ats) {
		return repo.findAllByActivityActivityIn(ats);
	}

	@Cacheable("SdeBlueprintFindProducts")
	public List<Product> findProducts(int bpTypeId, ActivityType ats) {
		return findProducts(List.of(bpTypeId), List.of(ats));
	}

	public List<Product> findProducers(Iterable<Integer> productIds, Iterable<ActivityType> ats) {
		return repo.findAllByTypeIdInAndActivityActivityIn(productIds, ats);
	}

	@Cacheable("SdeBlueprintFindProducers")
	public List<Product> findProducers(int productId,
			ActivityType activity) {
		return findProducers(List.of(productId), List.of(activity));
	}

	/**
	 * request to load in the cache the product type and bp type of each of the
	 * lines.
	 *
	 * @param products
	 * @return
	 */
	public List<Type[]> loadTypes(Collection<Product> products) {
		return repo.loadTypes(
		    products.stream().map(Product::getId).distinct().toList());
	}

	public static final List<String> CACHE_LIST = List.of(
			"SdeBlueprintFindProducts",
			"SdeBlueprintFindProducers");

	@Override
	public List<String> listSDECaches() {
		return CACHE_LIST;
	}

}
