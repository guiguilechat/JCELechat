package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Service
public class TypeAttributeService {

	@Getter(value = AccessLevel.PACKAGE)
	@Accessors(fluent = true)
	private final TypeAttributeRepository repo;

	public void delete() {
		repo().delete();
	}

	//
	// usage
	//

	public List<TypeAttribute> byAttributeId(int attributeId) {
		return repo().findAllByAttributeId(attributeId);
	}

	public List<TypeAttribute> byAttribute(Attribute attribute) {
		return repo().findAllByAttributeId(attribute.getId());
	}

	public List<TypeAttribute> findAll() {
		return repo().findAll();
	}

	public Map<Integer, Number> valuesForTypes(int attributeId, Iterable<Integer> typeIds) {
		return repo().listValuesByAttributeIdTypeIdIn(attributeId, typeIds).stream()
				.collect(Collectors.toMap(arr -> (Integer) arr[0], arr -> ((Number) arr[1])));
	}

	public TypeAttribute save(TypeAttribute entity) {
		return repo().save(entity);
	}

	public List<TypeAttribute> saveAll(Iterable<TypeAttribute> entities) {
		return repo().saveAll(entities);
	}

}
