package fr.guiguilechat.jcelechat.libs.spring.industry.activity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivities;
import fr.guiguilechat.jcelechat.libs.spring.gameclient.updater.GameClientUpdateListener;
import fr.guiguilechat.jcelechat.model.formula.industry.Activity;
import fr.guiguilechat.jcelechat.model.formula.industry.Activity.Type;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndustryActivityUpdater implements GameClientUpdateListener {

	private final IndustryActivityRepository repo;

	@Lazy
	private final IndustryActivityService industryActivityService;

	@Override
	public void beforeGameClientUpdate() {
		repo.deleteAllInBatch();
	}

	@SneakyThrows
	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void onGameClientUpdate(ClientCache cache) {
		List<KeyValTime<EindustryActivities>> loaded = EindustryActivities.getLoader().load(cache);
		List<IndustryActivity> translated = loaded.stream().map(l -> IndustryActivity.builder()
				.id(l.getVal().activityID)
				.name(l.getVal().activityName)
				.description(l.getVal().description)
				.build())
				.toList();
		translated.forEach(ia -> addAliases(ia));
		repo.saveAllAndFlush(translated);
		industryActivityService.resetCaches(translated);
		log.info("imported {} activities", translated.size());
	}

	private static final String ALIAS_SEP = "/";

	protected void addAliases(IndustryActivity ia) {
		Type t = Activity.Type.of(ia.getId());
		if (t != null) {
			ia.setAliases(ALIAS_SEP + t.getLowerNames().stream().collect(Collectors.joining(ALIAS_SEP)) + ALIAS_SEP);
		} else {
			System.err.println("unknown activity id " + ia.getId());
		}
	}

}
