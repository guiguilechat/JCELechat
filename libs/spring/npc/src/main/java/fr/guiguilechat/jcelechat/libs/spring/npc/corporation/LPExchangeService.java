package fr.guiguilechat.jcelechat.libs.spring.npc.corporation;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LPExchangeService {

	final private LPExchangeRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<LPExchange> saveAll(Iterable<LPExchange> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public LPExchange save(LPExchange entity) {
		return repo.saveAndFlush(entity);
	}

}
