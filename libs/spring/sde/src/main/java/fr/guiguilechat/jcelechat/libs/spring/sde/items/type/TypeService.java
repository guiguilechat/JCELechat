package fr.guiguilechat.jcelechat.libs.spring.sde.items.type;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TypeService extends SdeEntityService<Type, Integer, TypeRepository> {

	public TypeService() {
		super(Type::new);
	}

	public List<Type> byGroupId(int groupId) {
		return repo().findByGroupId(groupId);
	}

	public List<Type> byGroupIdIn(Iterable<Integer> groupIds) {
		return repo().findByGroupIdIn(groupIds);
	}

	public List<Type> byCategoryId(int categoryId) {
		return repo().findByGroupCategoryId(categoryId);
	}

	public List<Type> byCategoryIdIn(Iterable<Integer> categoryIds) {
		return repo().findByGroupCategoryIdIn(categoryIds);
	}

	public List<Integer> listVariationIds(int typeId) {
		return repo().listVariationIds(typeId);
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
		if (typeName == null || typeName.isBlank()) {
			return ret;
		}
		if (ret.isEmpty()) {
			ret = repo().findByNameEqualsIgnoreCase(typeName);
		}
		if (ret.isEmpty()) {
			ret = repo().findByNameStartsWithIgnoreCase(typeName);
		}
		if (ret.isEmpty()) {
			ret = repo().findByNameContainsIgnoreCase(typeName);
		}
		if (ret.isEmpty()) {
			ret = searchByNameTokens(typeName);
		}
		return ret;
	}

	public List<Type> searchByNameTokens(String tokens) {
		List<Type> ret = List.of();
		// have the biggest terms first to reduce the initial query the most.
		List<String> requiredTerms = Stream.of((tokens == null ? "" : tokens).split(" "))
				.filter(t -> t != null && !t.isBlank() && !t.startsWith("-") && t.length() > 1)
				.map(String::toLowerCase)
				.distinct()
				.sorted(Comparator.comparing(s -> -s.length()))
				.toList();
		List<String> ignoredTerms = Stream.of((tokens == null ? "" : tokens).split(" "))
				.filter(t -> t != null && !t.isBlank() && t.startsWith("-"))
				.map(s -> s.toLowerCase().substring(1))
				.distinct()
				.toList();
		log.trace("searching name for tokens " + requiredTerms + " ignore " + ignoredTerms);
		if (!requiredTerms.isEmpty()) {
			ret = null;
			for (String required : requiredTerms) {
				if (ret == null) {
					ret = repo().findByNameContainsIgnoreCase(required);
					log.trace("initial query [" + required + "] gets " + ret.size() + " results");
				} else {
					ret = ret.stream()
							.filter(
									t -> t.name().toLowerCase().contains(required))
							.toList();
					log.trace("query requiring [" + required + "] is size " + ret.size());
				}
			}
			if (!ignoredTerms.isEmpty() && !ret.isEmpty()) {
				for (String ignore : ignoredTerms) {
					ret = ret.stream()
							.filter(
									t -> !t.name().toLowerCase().contains(ignore))
							.toList();
					log.trace("query ignoring [" + ignore + "] is size " + ret.size());
				}
			}
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
			ret = repo().findByGroupNameEqualsIgnoreCase(groupName);
		}
		if (ret.isEmpty()) {
			ret = repo().findByGroupNameStartsWithIgnoreCase(groupName);
		}
		if (ret.isEmpty()) {
			ret = repo().findByGroupNameContainsIgnoreCase(groupName);
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
			ret = repo().findByGroupCategoryNameEqualsIgnoreCase(categoryName);
		}
		if (ret.isEmpty()) {
			ret = repo().findByGroupCategoryNameStartsWithIgnoreCase(categoryName);
		}
		if (ret.isEmpty()) {
			ret = repo().findByGroupCategoryNameContainsIgnoreCase(categoryName);
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
		case "id", "ti", "tid", "typeid" -> List.of(byId(Integer.parseInt(typeFilter)));
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
		case "id", "ti", "tid", "typeid" -> byId(Integer.parseInt(typeFilter));
		case "name", "tn", "tname", "typename" -> searchByName(typeFilter).stream().findFirst().orElse(null);
		default -> search(typeFilter).stream().findFirst().orElse(null);
		};
	}

	public Type prevType(Type type) {
		return repo().findTop1ByGroupAndNameLessThanOrderByNameDesc(type.getGroup(), type.name());
	}

	public Type nextType(Type type) {
		return repo().findTop1ByGroupAndNameGreaterThanOrderByNameAsc(type.getGroup(), type.name());
	}

}
