package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.TypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {

	final private TypeRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Type> saveAll(Iterable<Type> entities) {
		return repo.saveAll(entities);
	}

	public Type save(Type entity) {
		return repo.save(entity);
	}

	public Optional<Type> byId(int typeId) {
		return repo.findById(typeId);
	}

	public Map<Integer, Type> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(Type::getTypeId, c -> c));
	}

	public List<Type> byIdIn(Iterable<Integer> typeIds) {
		return repo.findByTypeIdIn(typeIds);
	}

	public List<Type> byGroupId(int groupId) {
		return repo.findByGroupGroupId(groupId);
	}

	public List<Type> byGroupIdIn(Iterable<Integer> groupIds) {
		return repo.findByGroupGroupIdIn(groupIds);
	}

	public List<Type> byCategoryId(int categoryId) {
		return repo.findByGroupCategoryCategoryId(categoryId);
	}

	public List<Type> byCategoryIdIn(Iterable<Integer> categoryIds) {
		return repo.findByGroupCategoryCategoryIdIn(categoryIds);
	}

	/**
	 * performs a list of queries to search for a type matching a given name :
	 * <ol>
	 * <li>type name equals ignore case</li>
	 * <li>type name starts with ignore case</li>
	 * <li>type name contains ignore case</li>
	 * </ol>
	 *
	 * @return the result of whichever queries is not empty first, or empty list
	 */
	public List<Type> searchByName(String typeName) {
		List<Type> ret = List.of();
		if (ret.isEmpty()) {
			ret = repo.findByNameEqualsIgnoreCase(typeName);
		}
		if (ret.isEmpty()) {
			ret = repo.findByNameStartsWithIgnoreCase(typeName);
		}
		if (ret.isEmpty()) {
			ret = repo.findByNameContainsIgnoreCase(typeName);
		}
		return ret;
	}

	/**
	 * performs a list of queries to search for a type matching a given name :
	 * <ol>
	 * <li>group name equals ignore case</li>
	 * <li>group name starts with ignore case</li>
	 * <li>group name contains ignore case</li>
	 * </ol>
	 *
	 * @return the result of whichever queries is not empty first, or empty list
	 */
	public List<Type> searchByGroupName(String groupName) {
		List<Type> ret = List.of();
		if (ret.isEmpty()) {
			ret = repo.findByGroupNameEqualsIgnoreCase(groupName);
		}
		if (ret.isEmpty()) {
			ret = repo.findByGroupNameStartsWithIgnoreCase(groupName);
		}
		if (ret.isEmpty()) {
			ret = repo.findByGroupNameContainsIgnoreCase(groupName);
		}
		return ret;
	}

	/**
	 * performs a list of queries to search for a type matching a given name :
	 * <ol>
	 * <li>category name equals ignore case</li>
	 * <li>category name starts with ignore case</li>
	 * <li>category name contains ignore case</li>
	 * </ol>
	 *
	 * @return the result of whichever queries is not empty first, or empty list
	 */
	public List<Type> searchByCategoryName(String categoryName) {
		List<Type> ret = List.of();
		if (ret.isEmpty()) {
			ret = repo.findByGroupCategoryNameEqualsIgnoreCase(categoryName);
		}
		if (ret.isEmpty()) {
			ret = repo.findByGroupCategoryNameStartsWithIgnoreCase(categoryName);
		}
		if (ret.isEmpty()) {
			ret = repo.findByGroupCategoryNameContainsIgnoreCase(categoryName);
		}
		return ret;
	}

	/**
	 * performs a list of queries to search for a type matching a given name :
	 * <ol>
	 * <li>{@link #searchByName(String)}</li>
	 * <li>{@link #searchByGroupName(String)}</li>
	 * <li>{@link #searchByCategoryName(String)}</li>
	 * </ol>
	 *
	 * @param targetName name to find a corresponding type
	 * @return first non-empty list of types, or empty list
	 */
	public List<Type> search(String targetName) {
		List<Type> ret = searchByName(targetName);
		if (ret.isEmpty()) {
			ret = searchByGroupName(targetName);
		}
		if (ret.isEmpty()) {
			ret = searchByCategoryName(targetName);
		}
		return ret;
	}

	public List<Type> typesFilter(String typeFiltering, String typeFilter) {
		return switch (Objects.requireNonNullElse(typeFiltering, "name").toLowerCase()) {
			case "id", "ti", "tid", "typeid" -> List.of(byId(Integer.parseInt(typeFilter)).orElse(null));
			case "name", "tn", "tname", "typename" -> searchByName(typeFilter);
			case "gn", "gname", "groupname" -> searchByGroupName(typeFilter);
			case "gi", "gid", "groupid" -> byGroupId(Integer.parseInt(typeFilter));
			case "cn", "cname", "categoryname" -> searchByCategoryName(typeFilter);
			case "ci", "cid", "categoryid" -> byCategoryId(Integer.parseInt(typeFilter));
			default -> search(typeFilter);
		};
	}

	public Type typeFilter(String typeFiltering, String typeFilter) {
		return switch (Objects.requireNonNullElse(typeFiltering, "name").toLowerCase()) {
			case "id", "ti", "tid", "typeid" -> byId(Integer.parseInt(typeFilter)).orElse(null);
			case "name", "tn", "tname", "typename" -> searchByName(typeFilter).stream().findFirst().orElse(null);
			default -> search(typeFilter).stream().findFirst().orElse(null);
		};
	}

	public Type prevType(Type type) {
		return repo.findTop1ByGroupAndNameLessThanOrderByNameDesc(type.getGroup(), type.getName());
	}

	public Type nextType(Type type) {
		return repo.findTop1ByGroupAndNameGreaterThanOrderByNameAsc(type.getGroup(), type.getName());
	}

}
