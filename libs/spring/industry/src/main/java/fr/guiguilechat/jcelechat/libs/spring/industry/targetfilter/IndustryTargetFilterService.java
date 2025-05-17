package fr.guiguilechat.jcelechat.libs.spring.industry.targetfilter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
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

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
@Order(3) // depends on groups, categories
public class IndustryTargetFilterService implements GameClientUpdateListener {

	final private IndustryTargetFilterRepository repo;

	public List<IndustryTargetFilter> saveAll(Iterable<IndustryTargetFilter> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public IndustryTargetFilter save(IndustryTargetFilter entity) {
		return repo.saveAndFlush(entity);
	}

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
		saveAll(translated);
		log.info("imported {} filters", translated.size());
	}

	public Map<Integer, IndustryTargetFilter> byId(Iterable<Integer> ids) {
		return repo.findAllById(ids).stream()
				.collect(Collectors.toMap(IndustryTargetFilter::getId, itf -> itf));
	}

	public Map<Integer, IndustryTargetFilter> all() {
		return repo.findAll().stream()
				.collect(Collectors.toMap(IndustryTargetFilter::getId, itf -> itf));
	}

}
