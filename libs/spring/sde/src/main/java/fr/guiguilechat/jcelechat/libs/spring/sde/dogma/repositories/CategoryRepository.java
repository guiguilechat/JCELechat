package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findTop1ByNameLessThanOrderByNameDesc(String name);

	public Category findTop1ByNameGreaterThanOrderByNameAsc(String name);

}
