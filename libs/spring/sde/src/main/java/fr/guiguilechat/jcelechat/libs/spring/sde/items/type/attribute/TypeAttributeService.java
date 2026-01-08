package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttributeRepository.SkillIdMaxRequired;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttributeRepository.TypeIdValue;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class TypeAttributeService extends DeducedEntityService<TypeAttribute, TypeAttributeRepository> {

	public List<TypeAttribute> byAttributeId(int attributeId) {
		return repo().findAllByAttributeId(attributeId);
	}

	public List<TypeAttribute> byAttribute(Attribute attribute) {
		return repo().findAllByAttributeId(attribute.getId());
	}

	public List<TypeAttribute> byTypeId(Iterable<Integer> typeIds) {
		return repo().findAllByTypeIdIn(typeIds);
	}

	public List<TypeAttribute> findAll() {
		return repo().findAll();
	}

	public Map<Integer, Number> valuesForTypes(int attributeId, Iterable<Integer> typeIds) {
		return repo().listValuesByAttributeIdTypeIdIn(attributeId, typeIds).stream()
				.collect(Collectors.toMap(TypeIdValue::typeId, TypeIdValue::attributeValue));
	}

	public static record RequiredSkill(int id, int level) {
		public static RequiredSkill of(SkillIdMaxRequired source) {
			return new RequiredSkill(source.skillTypeId(), source.maxRequired().intValue());
		}
	}

	public List<RequiredSkill> requiredSkills(Iterable<Integer> typeIds) {
		return repo().requiredSkills(typeIds).stream()
				.map(RequiredSkill::of)
				.toList();
	}

}
