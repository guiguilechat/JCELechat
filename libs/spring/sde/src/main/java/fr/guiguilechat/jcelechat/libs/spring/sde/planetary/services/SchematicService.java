package fr.guiguilechat.jcelechat.libs.spring.sde.planetary.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.Schematic;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.repositories.SchematicRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchematicService {

	final private SchematicRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public Schematic save(Schematic entity) {
		return repo.save(entity);
	}

	public List<Schematic> saveAll(Iterable<Schematic> entities) {
		return repo.saveAll(entities);
	}

	public List<Schematic> fetchAll() {
		return repo.fetchAll();
	}

}
