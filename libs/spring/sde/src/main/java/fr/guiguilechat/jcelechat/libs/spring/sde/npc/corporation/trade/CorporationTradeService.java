package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.trade;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CorporationTradeService {

	final private CorporationTradeRepository repo;

	/**
	 * remove all existing entries from the DB
	 */
	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<CorporationTrade> saveAll(Iterable<CorporationTrade> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public CorporationTrade save(CorporationTrade entity) {
		return repo.saveAndFlush(entity);
	}

}
