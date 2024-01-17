package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public void clear() {
		repo.deleteAll();
		repo.flush();
	}

	public List<Category> saveAll(Iterable<Category> entities) {
		return repo.saveAll(entities);
	}

	public Category save(Category entity) {
		return repo.save(entity);
	}

}
