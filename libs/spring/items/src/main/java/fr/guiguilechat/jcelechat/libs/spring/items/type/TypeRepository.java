package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface TypeRepository extends IRemoteEntityRepository<Type, Integer> {

	List<Type> findByGroupId(int groupId);

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

	List<Type> findByGroupIdIn(Iterable<Integer> groupIds);

	List<Type> findByGroupCategoryIdIn(Iterable<Integer> categoryIds);

	@Query("""
select
	v.id id
from
	#{#entityName} t
	join #{#entityName} v on
		t.id=v.id
		or t.variationTypeId=v.id
		or t.id=v.variationTypeId
		or t.variationTypeId is not null and t.variationTypeId=v.variationTypeId
where
	t.id=:typeId
order by
	v.id asc
""")
	List<Integer> listVariationIds(int typeId);

}
