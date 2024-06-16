package fr.guiguilechat.jcelechat.libs.spring.items.type;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.IRemoteFetchedResourceRepository;

public interface CategoryRepository extends IRemoteFetchedResourceRepository<Category, Integer> {

	public Category findTop1ByNameLessThanOrderByNameDesc(String name);

	public Category findTop1ByNameGreaterThanOrderByNameAsc(String name);

}
