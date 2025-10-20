package fr.guiguilechat.jcelechat.libs.spring.sde.items.attribute;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class AttributeService
		extends SdeEntityService<
        Attribute,
        Integer,
				AttributeRepository> {

	public AttributeService() {
		super(Attribute::new);
	}

	public static record RequiredSkill(int id, int level) {
	}

	public List<RequiredSkill> requiredSkills(Iterable<Integer> typeIds) {
		return repo().requiredSkills(typeIds).stream()
				.map(arr -> new RequiredSkill(((Number) arr[0]).intValue(), ((Number) arr[1]).intValue()))
				.toList();
	}

}
