package fr.guiguilechat.jcelechat.libs.spring.industry.activity.targetfilter;


import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivityTargetFilters;
import fr.guiguilechat.jcelechat.libs.spring.gameclient.updater.GameClientUpdateListener;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndustryTargetFilterUpdater implements GameClientUpdateListener {

	final private IndustryTargetFilterRepository repo;

	@Override
	public void beforeGameClientUpdate() {
		repo.deleteAllInBatch();
	}

	@SneakyThrows
	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void onGameClientUpdate(ClientCache cache) {
		List<KeyValTime<EindustryActivityTargetFilters>> loaded = EindustryActivityTargetFilters.getLoader()
				.load(cache);
		List<IndustryTargetFilter> translated = loaded.stream().map(l -> IndustryTargetFilter.builder()
				.id(l.getKey())
				.name(l.getVal().name)
				.categoryIds(l.getVal().categoryIDs)
				.groupIds(l.getVal().groupIDs)
				.build())
				.toList();
		repo.saveAllAndFlush(translated);
		log.info("imported {} filters", translated.size());
	}

}
