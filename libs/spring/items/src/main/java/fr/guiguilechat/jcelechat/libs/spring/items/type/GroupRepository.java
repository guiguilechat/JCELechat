package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.IRemoteFetchedResourceRepository;

public interface GroupRepository extends IRemoteFetchedResourceRepository<Group, Integer> {

	public List<Group> findByNameEqualsIgnoreCase(String name);

	public List<Group> findByCategoryId(int catId);

	public Group findTop1ByCategoryAndNameGreaterThanOrderByNameAsc(Category category, String name);

	public Group findTop1ByCategoryAndNameLessThanOrderByNameDesc(Category category, String name);

}
