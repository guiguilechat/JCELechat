package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping.MutaMappingRepository.MutaProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
public class MutaMappingService extends DeducedEntityService<MutaMapping, MutaMappingRepository>
		implements SdeListener {

	public List<MutaProduct> listProducts() {
		return repo().listProducts();
	}

	@Transactional
	public List<Type> sourcesFor(int abyssalTypeId) {
		return repo().sourcesFor(abyssalTypeId);
	}

	public record GroupProducts(Group group, List<Type> types) {
	}

	public record CategoryProducts(Category category, List<GroupProducts> groups) {
	}

	@Transactional
	@Cacheable("MutaplasmidsCategoryProducts")
	public List<CategoryProducts> listCategoryProducts() {
		List<CategoryProducts> ret = new ArrayList<>();
		CategoryProducts lastCat = null;
		GroupProducts lastGroup = null;
		for (MutaProduct mp : listProducts()) {
			if (lastCat == null || lastCat.category().getId() != mp.category().getId()) {
				lastCat = new CategoryProducts(mp.category(), new ArrayList<>());
				ret.add(lastCat);
				lastGroup = null;
			}
			if (lastGroup == null || lastGroup.group().getId() != mp.group().getId()) {
				lastGroup = new GroupProducts(mp.group(), new ArrayList<>());
				lastCat.groups().add(lastGroup);
			}
			lastGroup.types().add(mp.type());
		}
		return ret;
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final List<String> listSDECaches = List.of(
			"MutaplasmidsCategoryProducts"
	//
	);

}
