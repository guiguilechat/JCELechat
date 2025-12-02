package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.lpexchange;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LPExchangeService {

	final private LPExchangeRepository repo;

	/**
	 * remove all existing entries from the DB
	 */
	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<LPExchange> saveAll(Iterable<LPExchange> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public LPExchange save(LPExchange entity) {
		return repo.saveAndFlush(entity);
	}

	public List<LPExchange> to(int targetCorporationId) {
		return repo.findByTargetCorporationId(targetCorporationId);
	}

	public List<LPExchange> from(int sourceCorporationId) {
		return repo.findBySourceCorporationId(sourceCorporationId);
	}

}
