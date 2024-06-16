package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.TypeAttribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.TypeAttributeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Deprecated
public class TypeAttributeService {

	final private TypeAttributeRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<TypeAttribute> saveAll(Iterable<TypeAttribute> entities) {
		return repo.saveAll(entities);
	}

	public TypeAttribute save(TypeAttribute entity) {
		return repo.save(entity);
	}

	public List<TypeAttribute> byAttributeId(int attributeId) {
		return repo.findAllByAttributeAttributeId(attributeId);
	}

	public List<TypeAttribute> byAttributeId(Attribute attribute) {
		return repo.findAllByAttributeAttributeId(attribute.getAttributeId());
	}

}
