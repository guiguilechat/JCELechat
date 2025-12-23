package fr.guiguilechat.jcelechat.libs.spring.anon.industry.planetary;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
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
