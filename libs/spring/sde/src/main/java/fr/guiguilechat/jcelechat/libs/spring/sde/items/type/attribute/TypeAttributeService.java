package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TypeAttributeService extends DeducedEntityService<TypeAttribute, TypeAttributeRepository> {

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

}
