package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResourceService;
import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.items.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.items.effect.Effect;
import fr.guiguilechat.jcelechat.libs.spring.items.effect.EffectService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SDEUpdateService.SdeUpdateListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Etypes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.type")
// depends on group effect attribute
@Order(3)
public class TypeService
    extends ARemoteResourceService<Type, Integer, R_get_universe_types_type_id, TypeRepository>
    implements SdeUpdateListener {

	@Lazy
	private final AttributeService attributeService;

	@Lazy
	private final EffectService effectService;

	@Lazy
	private final GroupService groupService;

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
	    Map<Integer, Effect> idToEffect) {
		data.setGroup(idToGroup.get(received.group_id));
		typeAttributeService.deleteByType(data);
		if (received.dogma_attributes != null) {
			List<TypeAttribute> typeAttributes = new ArrayList<>();
			for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes a : received.dogma_attributes) {
				Attribute att = idToAttribute.get(a.attribute_id);
				typeAttributes.add(TypeAttribute.builder()
				    .attribute(att)
				    .type(data)
				    .build());
			}
			typeAttributeService.saveAll(typeAttributes);
		}
		if (received.dogma_effects != null) {
			for (get_dogma_dynamic_items_type_id_item_id_dogma_effects e : received.dogma_effects) {
				Effect eff = idToEffect.get(e.effect_id);
				data.getEffectsDefault().put(eff, e.is_default);
			}
		}
	}

	@Override
	protected void updateResponseOk(Map<Type, R_get_universe_types_type_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, Group> idToGroup = groupService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.group_id).distinct().toList());
		Map<Integer, Attribute> idToAttribute = attributeService
		    .createIfAbsent(responseOk.values().stream()
		        .flatMap(r -> r.dogma_attributes == null ? Stream.empty() : Stream.of(r.dogma_attributes))
		        .map(da -> da.attribute_id).distinct().toList());
		Map<Integer, Effect> idToEffect = effectService
		    .createIfAbsent(responseOk.values().stream()
		        .flatMap(r -> r.dogma_effects == null ? Stream.empty() : Stream.of(r.dogma_effects))
		        .map(da -> da.effect_id).distinct().toList());
		responseOk.entrySet().stream()
		    .forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToGroup, idToAttribute, idToEffect));
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
			ret = repo().findByNameEqualsIgnoreCase(typeName);
		}
		if (ret.isEmpty()) {
			ret = repo().findByNameStartsWithIgnoreCase(typeName);
		}
		if (ret.isEmpty()) {
			ret = repo().findByNameContainsIgnoreCase(typeName);
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
		return repo().findTop1ByGroupAndNameLessThanOrderByNameDesc(type.getGroup(), type.getName());
	}

	public Type nextType(Type type) {
		return repo().findTop1ByGroupAndNameGreaterThanOrderByNameAsc(type.getGroup(), type.getName());
	}

	static final Pattern ENTRYNAME_TYPES_PATTERN = Pattern.compile(
	    "fsd/types\\.yaml");

	@Override
	public void onSdeFile(String entryName, Supplier<InputStream> fileContent) {
		Matcher matcher = ENTRYNAME_TYPES_PATTERN.matcher(entryName);
		if (matcher.matches()) {

			try (InputStream is = fileContent.get()) {
				LinkedHashMap<Integer, Etypes> newTypes = Etypes.from(is);
				Map<Integer, Type> existingTypes = allById();
				for (Entry<Integer, Etypes> e : newTypes.entrySet()) {
					Type existing = existingTypes.get(e.getKey());
					if (existing == null) {
						existing = createIfAbsent(e.getKey());
						existingTypes.put(e.getKey(), existing);
					}
					existing.setBasePrice(e.getValue().basePrice);
				}
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
	}

}
