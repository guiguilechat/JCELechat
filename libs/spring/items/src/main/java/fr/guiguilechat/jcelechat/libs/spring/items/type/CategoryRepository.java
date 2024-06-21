package fr.guiguilechat.jcelechat.libs.spring.items.type;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;

public interface CategoryRepository extends IRemoteResourceRepository<Category, Integer> {

	public Category findTop1ByNameLessThanOrderByNameDesc(String name);

	public Category findTop1ByNameGreaterThanOrderByNameAsc(String name);

}
