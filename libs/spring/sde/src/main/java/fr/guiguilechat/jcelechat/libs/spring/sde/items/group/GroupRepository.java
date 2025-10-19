package fr.guiguilechat.jcelechat.libs.spring.sde.items.group;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface GroupRepository extends SdeEntityRepository<Group, Integer> {

	List<Group> findByNameEqualsIgnoreCase(String name);

	List<Group> findByNameContainsIgnoreCase(String name);

	List<Group> findByNameInIgnoreCase(Iterable<String> names);

	List<Group> findByCategoryId(int catId);

	Group findTop1ByCategoryAndNameGreaterThanOrderByNameAsc(Category category, String name);

	Group findTop1ByCategoryAndNameLessThanOrderByNameDesc(Category category, String name);

}
