package fr.guiguilechat.jcelechat.libs.spring.anon.industry.planetary;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SchemMaterialService {

	final private SchemMaterialRepository repo;

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
