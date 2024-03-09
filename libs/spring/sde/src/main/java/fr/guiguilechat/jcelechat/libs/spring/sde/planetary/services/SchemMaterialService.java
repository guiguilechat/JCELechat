package fr.guiguilechat.jcelechat.libs.spring.sde.planetary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.SchemMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.repositories.SchemMaterialRepository;

@Service
public class SchemMaterialService {

	@Autowired
	private SchemMaterialRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public SchemMaterial save(SchemMaterial entity) {
		return repo.save(entity);
	}

	public List<SchemMaterial> saveAll(Iterable<SchemMaterial> entities) {
		return repo.saveAll(entities);
	}

}
