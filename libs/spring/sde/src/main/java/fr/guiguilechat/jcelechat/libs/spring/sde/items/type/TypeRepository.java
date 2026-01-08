package fr.guiguilechat.jcelechat.libs.spring.sde.items.type;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface TypeRepository extends SdeEntityRepository<Type, Integer> {

	List<Type> findByGroupIdOrderByName(int groupId);

	List<Type> findByGroupCategoryId(int categoryId);

	List<Type> findByNameEqualsIgnoreCase(String typeName);

	List<Type> findByNameStartsWithIgnoreCase(String typeName);

	List<Type> findByNameContainsIgnoreCase(String typeName);

	List<Type> findByGroupNameEqualsIgnoreCase(String groupName);

	List<Type> findByGroupNameStartsWithIgnoreCase(String groupName);

	List<Type> findByGroupNameContainsIgnoreCase(String groupName);

	List<Type> findByGroupCategoryNameEqualsIgnoreCase(String categoryName);

	List<Type> findByGroupCategoryNameStartsWithIgnoreCase(String categoryName);

	List<Type> findByGroupCategoryNameContainsIgnoreCase(String categoryName);

	Type findTop1ByGroupAndNameLessThanOrderByNameDesc(Group group, String name);

	Type findTop1ByGroupAndNameGreaterThanOrderByNameAsc(Group group, String name);

	List<Type> findByGroupIdInOrderByName(Iterable<Integer> groupIds);

	List<Type> findByGroupCategoryIdIn(Iterable<Integer> categoryIds);

	@Query("""
select
	v.id id
from
	#{#entityName} t
	join #{#entityName} v on
		t.id=v.id
		or t.variationType.id=v.id
		or t.id=v.variationType.id
		or t.variationType.id is not null and t.variationType.id=v.variationType.id
where
	t.id=:typeId
order by
	v.id asc
""")
	List<Integer> listVariationIds(int typeId);

}
