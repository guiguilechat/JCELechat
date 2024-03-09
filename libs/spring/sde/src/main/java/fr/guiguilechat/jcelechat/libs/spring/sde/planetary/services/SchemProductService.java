package fr.guiguilechat.jcelechat.libs.spring.sde.planetary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.SchemProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.repositories.SchemProductRepository;

@Service
public class SchemProductService {

	@Autowired
	private SchemProductRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public SchemProduct save(SchemProduct entity) {
		return repo.save(entity);
	}

	public List<SchemProduct> saveAll(Iterable<SchemProduct> entities) {
		return repo.saveAll(entities);
	}

}
