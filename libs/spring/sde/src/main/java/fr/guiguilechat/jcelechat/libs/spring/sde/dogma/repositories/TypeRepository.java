package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {

	public List<Type> findByTypeIdIn(Iterable<Integer> typeIds);

	public List<Type> findByGroupGroupId(int groupId);

	public List<Type> findByGroupGroupIdIn(Iterable<Integer> groupIds);

	public List<Type> findByGroupCategoryCategoryId(int catId);

	public List<Type> findByGroupCategoryCategoryIdIn(Iterable<Integer> catIds);

	// name search

	public List<Type> findByNameEqualsIgnoreCase(String name);

	public List<Type> findByNameStartsWithIgnoreCase(String name);

	public List<Type> findByNameContainsIgnoreCase(String name);

	public List<Type> findByGroupNameEqualsIgnoreCase(String name);

	public List<Type> findByGroupNameStartsWithIgnoreCase(String name);

	public List<Type> findByGroupNameContainsIgnoreCase(String name);

	public List<Type> findByGroupCategoryNameEqualsIgnoreCase(String name);

	public List<Type> findByGroupCategoryNameStartsWithIgnoreCase(String name);

	public List<Type> findByGroupCategoryNameContainsIgnoreCase(String name);

	// navigate

	public Type findTop1ByGroupAndNameGreaterThanOrderByNameAsc(Group group, String name);

	public Type findTop1ByGroupAndNameLessThanOrderByNameDesc(Group group, String name);

}
