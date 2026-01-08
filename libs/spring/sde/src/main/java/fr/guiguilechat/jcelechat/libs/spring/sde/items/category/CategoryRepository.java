package fr.guiguilechat.jcelechat.libs.spring.sde.items.category;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface CategoryRepository extends SdeEntityRepository<Category, Integer> {

	Category findTop1ByNameLessThanOrderByNameDesc(String name);

	Category findTop1ByNameGreaterThanOrderByNameAsc(String name);

	List<Category> findByOrderByNameAsc();

}
