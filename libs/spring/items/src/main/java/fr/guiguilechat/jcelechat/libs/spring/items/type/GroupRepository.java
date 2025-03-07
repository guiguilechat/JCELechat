package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface GroupRepository extends IRemoteEntityRepository<Group, Integer> {

	List<Group> findByNameEqualsIgnoreCase(String name);

	List<Group> findByNameContainsIgnoreCase(String name);

	List<Group> findByNameInIgnoreCase(Iterable<String> names);

	List<Group> findByCategoryId(int catId);

	Group findTop1ByCategoryAndNameGreaterThanOrderByNameAsc(Category category, String name);

	Group findTop1ByCategoryAndNameLessThanOrderByNameDesc(Category category, String name);

}
