package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

	public List<Group> findByNameEqualsIgnoreCase(String name);

	public List<Group> findByCategoryCategoryId(int catId);

	public Group findTop1ByCategoryAndNameGreaterThanOrderByNameAsc(Category category, String name);

	public Group findTop1ByCategoryAndNameLessThanOrderByNameDesc(Category category, String name);

}
