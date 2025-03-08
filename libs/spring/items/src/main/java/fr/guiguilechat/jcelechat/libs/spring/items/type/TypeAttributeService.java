package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TypeAttributeService {

	private final TypeAttributeRepository repo;

	public void deleteByTypes(Collection<Type> types) {
		repo.deleteByTypeId(types.stream().map(Type::getId).toList());
	}

	public List<TypeAttribute> saveAll(Iterable<TypeAttribute> data) {
		return repo.saveAll(data);
	}

	//
	// usage
	//

	public List<TypeAttribute> byAttributeId(int attributeId) {
		return repo.findAllByAttributeId(attributeId);
	}

	public List<TypeAttribute> byAttribute(Attribute attribute) {
		return repo.findAllByAttribute(attribute);
	}

	public List<TypeAttribute> findAll() {
		return repo.findAll();
	}

	public Map<Integer, Number> valuesForTypes(int attributeId, Iterable<Integer> typeIds) {
		return repo.listValuesByAttributeIdTypeIdIn(attributeId, typeIds).stream()
				.collect(Collectors.toMap(arr -> (Integer) arr[0], arr -> ((Number) arr[1])));
	}

}
