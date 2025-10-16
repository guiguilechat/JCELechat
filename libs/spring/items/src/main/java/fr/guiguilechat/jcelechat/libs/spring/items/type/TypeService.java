package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.items.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.items.effect.Effect;
import fr.guiguilechat.jcelechat.libs.spring.items.effect.EffectService;
import fr.guiguilechat.jcelechat.libs.spring.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.items.marketgroup.MarketGroup;
import fr.guiguilechat.jcelechat.libs.spring.items.marketgroup.MarketGroupService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.AFetchedResourceService.EntityUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.type")
// depends on group effect attribute
@Order(3)
public class TypeService
extends ARemoteEntityService<Type, Integer, R_get_universe_types_type_id, TypeRepository>
		implements EntityUpdateListener {

	@Lazy
	private final AttributeService attributeService;

	@Lazy
	private final EffectService effectService;

	@Lazy
	private final GroupService groupService;

	@Lazy
	private final MarketGroupService marketGroupService;

	@Lazy
	private final TypeAttributeService typeAttributeService;

	@Override
	protected Type create(Integer entityId) {
		Type ret = new Type();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_types_type_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_universe_types((int) id, properties);
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE
				.requestGetPages((page, props) -> ESIRawPublic.INSTANCE.get_universe_types(page, props), p);
	}

	protected void updateResponseOk(Type data, R_get_universe_types_type_id received,
			Map<Integer, Group> idToGroup,
			Map<Integer, Attribute> idToAttribute,
			Map<Integer, Effect> idToEffect,
			Map<Integer, MarketGroup> idToMarketGroup) {
		data.setGroup(idToGroup.get(received.group_id));
		if (received.dogma_attributes != null) {
			List<TypeAttribute> typeAttributes = new ArrayList<>();
			for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes a : received.dogma_attributes) {
				Attribute att = idToAttribute.get(a.attribute_id);
				typeAttributes.add(TypeAttribute.builder()
						.attribute(att)
						.type(data)
						.value(BigDecimal.valueOf(a.value))
						.build());
			}
			typeAttributeService.saveAll(typeAttributes);
		}
		data.getEffectsDefault().clear();
		if (received.dogma_effects != null) {
			for (get_dogma_dynamic_items_type_id_item_id_dogma_effects e : received.dogma_effects) {
				Effect eff = idToEffect.get(e.effect_id);
				data.getEffectsDefault().put(eff, e.is_default);
			}
		}
		data.setMarketGroup(idToMarketGroup.get(received.market_group_id));
	}

	@Override
	protected void updateResponseOk(Map<Type, R_get_universe_types_type_id> responseOk) {
		super.updateResponseOk(responseOk);
		long startTime = System.currentTimeMillis();

		Map<Integer, Group> idToGroup = groupService.createIfAbsent(
				responseOk.values().stream()
				.map(r -> r.group_id)
				.distinct().toList());
		long postGroups = System.currentTimeMillis();

		Map<Integer, Attribute> idToAttribute = attributeService.createIfAbsent(
				responseOk.values().stream()
				.flatMap(r -> r.dogma_attributes == null ? Stream.empty() : Stream.of(r.dogma_attributes))
				.map(da -> da.attribute_id)
				.distinct().toList());
		long postAttributes = System.currentTimeMillis();

		typeAttributeService.deleteByTypes(responseOk.keySet());
		long postDeleteAtts = System.currentTimeMillis();

		Map<Integer, Effect> idToEffect = effectService.createIfAbsent(
				responseOk.values().stream()
				.flatMap(r -> r.dogma_effects == null ? Stream.empty() : Stream.of(r.dogma_effects))
				.map(da -> da.effect_id)
				.distinct().toList());
		long postEffects = System.currentTimeMillis();

		Map<Integer, MarketGroup> idToMarketGroup = marketGroupService.createIfAbsent(
				responseOk.values().stream()
				.map(r -> r.market_group_id)
				.filter(i -> i != 0)
				.distinct().toList());
		long postMarketGroups = System.currentTimeMillis();

		responseOk.entrySet().forEach(
				e -> updateResponseOk(e.getKey(), e.getValue(), idToGroup, idToAttribute, idToEffect, idToMarketGroup));
		long postUpdateElements = System.currentTimeMillis();
		log.debug(
				"processed {} received types in {} ms, fetchGroups={}ms fetchAtts={}ms deleteAtts={}ms fetchEffects={}ms fetchmkg={}ms processElemes={}ms",
				responseOk.size(),
				postUpdateElements - startTime,
				postGroups - startTime,
				postAttributes - postGroups,
				postDeleteAtts - postAttributes,
				postEffects - postDeleteAtts,
				postMarketGroups - postEffects,
				postUpdateElements - postMarketGroups);
	}

	@Cacheable("TypesByGroupId")
	public List<Type> byGroupId(int groupId) {
		return repo().findByGroupId(groupId);
	}

	@Cacheable("TypesByGroupIdIn")
	public List<Type> byGroupIdIn(Iterable<Integer> groupIds) {
		return repo().findByGroupIdIn(groupIds);
	}

	@Cacheable("TypesByCategoryId")
	public List<Type> byCategoryId(int categoryId) {
		return repo().findByGroupCategoryId(categoryId);
	}

	@Cacheable("TypesByCategoryIdIn")
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

	// cache

	public interface TypeListener extends EntityUpdateListener {
	}

	@Getter
	@Lazy
	private final Optional<List<TypeListener>> listeners;

	@Getter(lazy = true)
	private final boolean selfInvalidate = true;

	@Getter(lazy = true)
	private final List<String> cacheList = List.of(
			"TypesByCategoryId",
			"TypesByCategoryIdIn",
			"TypesByGroupId",
			"TypesByGroupIdIn");

}
