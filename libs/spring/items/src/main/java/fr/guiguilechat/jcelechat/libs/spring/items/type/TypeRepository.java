package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;

public interface TypeRepository extends IRemoteResourceRepository<Type, Integer> {

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

}
