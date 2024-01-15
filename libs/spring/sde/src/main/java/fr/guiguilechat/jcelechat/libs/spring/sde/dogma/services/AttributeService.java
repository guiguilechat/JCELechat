package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.AttributeRepository;

@Service
public class AttributeService {

	@Autowired
	private AttributeRepository repo;

	public void clear() {
		repo.deleteAll();
	}

	public List<Attribute> saveAll(Iterable<Attribute> entities) {
		return repo.saveAll(entities);
	}

	public Attribute save(Attribute entity) {
		return repo.save(entity);
	}

}
