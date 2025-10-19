package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SeededService {

	final private SeededRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Seeded> saveAll(Iterable<Seeded> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public Seeded save(Seeded entity) {
		return repo.saveAndFlush(entity);
	}

}
