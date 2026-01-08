package fr.guiguilechat.jcelechat.libs.spring.sde.items.category;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class CategoryService
		extends SdeEntityService<Category, Integer, CategoryRepository> {

	public CategoryService() {
		super(Category::new);
	}

	public List<Category> sortedByName() {
		return repo().findByOrderByNameAsc();
	}

	public Category prevGroup(Category c) {
		return repo().findTop1ByNameLessThanOrderByNameDesc(c.getName());
	}

	public Category nextGroup(Category c) {
		return repo().findTop1ByNameGreaterThanOrderByNameAsc(c.getName());
	}

}
