package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	final private CategoryRepository repo;

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

	public Map<Integer, Category> allById(){
		return repo.findAll().stream().collect(Collectors.toMap(Category::getCategoryId, c -> c));
	}

}
