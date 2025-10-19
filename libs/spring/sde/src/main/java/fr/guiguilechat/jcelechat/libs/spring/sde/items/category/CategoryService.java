package fr.guiguilechat.jcelechat.libs.spring.sde.items.category;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class CategoryService
		extends SdeEntityService<Category, Integer, CategoryRepository> {

	public CategoryService() {
		super(Category::new);
	}

	public Category prevGroup(Category c) {
		return repo().findTop1ByNameLessThanOrderByNameDesc(c.getName());
	}

	public Category nextGroup(Category c) {
		return repo().findTop1ByNameGreaterThanOrderByNameAsc(c.getName());
	}

}
