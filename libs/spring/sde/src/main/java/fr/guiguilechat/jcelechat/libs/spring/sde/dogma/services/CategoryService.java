package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Category> saveAll(Iterable<Category> entities) {
		return repo.saveAll(entities);
	}

	public Category save(Category entity) {
		return repo.save(entity);
	}

	public Optional<Category> byId(int categoryId) {
		return repo.findById(categoryId);
	}

	public Category prevGroup(Category c) {
		return repo.findTop1ByNameLessThanOrderByNameDesc(c.getName());
	}

	public Category nextGroup(Category c) {
		return repo.findTop1ByNameGreaterThanOrderByNameAsc(c.getName());
	}

}
