package fr.guiguilechat.jcelechat.libs.spring.items.category;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface CategoryRepository extends IRemoteEntityRepository<Category, Integer> {

	public Category findTop1ByNameLessThanOrderByNameDesc(String name);

	public Category findTop1ByNameGreaterThanOrderByNameAsc(String name);

}
