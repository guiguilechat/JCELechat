package fr.guiguilechat.jcelechat.libs.spring.npc.corporation;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NPCCorpService {

	final private NPCCorpRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<NPCCorp> saveAll(Iterable<NPCCorp> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public NPCCorp save(NPCCorp entity) {
		return repo.saveAndFlush(entity);
	}

}
