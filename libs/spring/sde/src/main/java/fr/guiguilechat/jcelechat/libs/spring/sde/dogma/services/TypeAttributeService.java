package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.TypeAttribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.TypeAttributeRepository;

@Service
public class TypeAttributeService {

	@Autowired
	private TypeAttributeRepository repo;

	public void clear() {
		repo.deleteAll();
	}

	public List<TypeAttribute> saveAll(Iterable<TypeAttribute> entities) {
		return repo.saveAll(entities);
	}

	public TypeAttribute save(TypeAttribute entity) {
		return repo.save(entity);
	}
}