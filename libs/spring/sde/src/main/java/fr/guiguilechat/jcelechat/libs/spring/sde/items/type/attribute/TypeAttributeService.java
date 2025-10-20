package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class TypeAttributeService extends SdeEntityService<TypeAttribute, Long, TypeAttributeRepository> {

	public TypeAttributeService() {
		super(TypeAttribute::new);
	}

	// instead of setting them to removed, actually remove them. A new SDE thus
	// removes all previous entries.
	@Override
	protected void setAllRemoved() {
		repo().deleteAll();
	}

	//
	// usage
	//

	public List<TypeAttribute> byAttributeId(int attributeId) {
		return repo().findAllByAttributeId(attributeId);
	}

	public List<TypeAttribute> byAttribute(Attribute attribute) {
		return repo().findAllByAttribute(attribute);
	}

	public List<TypeAttribute> findAll() {
		return repo().findAll();
	}

	public Map<Integer, Number> valuesForTypes(int attributeId, Iterable<Integer> typeIds) {
		return repo().listValuesByAttributeIdTypeIdIn(attributeId, typeIds).stream()
				.collect(Collectors.toMap(arr -> (Integer) arr[0], arr -> ((Number) arr[1])));
	}

}
