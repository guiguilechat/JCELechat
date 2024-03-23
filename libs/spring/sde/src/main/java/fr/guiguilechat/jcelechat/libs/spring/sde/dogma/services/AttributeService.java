package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.AttributeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttributeService {

	final private AttributeRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Attribute> saveAll(Iterable<Attribute> entities) {
		return repo.saveAll(entities);
	}

	public Attribute save(Attribute entity) {
		return repo.save(entity);
	}

	public Optional<Attribute> byId(int id) {
		return repo.findById(id);
	}

}
