package fr.guiguilechat.jcelechat.libs.spring.industry.activity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
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

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
@Order(1) // does not depend on anything.
public class IndustryActivityService implements GameClientUpdateListener {

	final private IndustryActivityRepository repo;

	public List<IndustryActivity> saveAll(Iterable<IndustryActivity> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public IndustryActivity save(IndustryActivity entity) {
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
		List<KeyValTime<EindustryActivities>> loaded = EindustryActivities.getLoader().load(cache);
		List<IndustryActivity> translated = loaded.stream().map(l -> IndustryActivity.builder()
				.id(l.getVal().activityID)
				.name(l.getVal().activityName)
				.description(l.getVal().description)
				.build())
				.toList();
		translated.forEach(ia -> addAliases(ia));
		saveAll(translated);
		resetCaches(translated);
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

	private Map<Integer, IndustryActivity> id2Activity = null;
	private Map<String, IndustryActivity> name2Activity = null;

	protected record Caches(
			Map<Integer, IndustryActivity> byIds,
			Map<String, IndustryActivity> byNames) {
	}

	protected Caches resetCaches(List<IndustryActivity> data) {
		Map<Integer, IndustryActivity> ids = id2Activity = data.stream()
				.collect(Collectors.toMap(IndustryActivity::getId, a -> a));
		Map<String, IndustryActivity> names = name2Activity = data.stream()
				.collect(Collectors.toMap(IndustryActivity::getName, a -> a));
		return new Caches(ids, names);
	}

	public Map<Integer, IndustryActivity> byId() {
		Map<Integer, IndustryActivity> ret = id2Activity;
		if (ret == null) {
			return resetCaches(repo.findAll()).byIds;
		}
		return ret;
	}

	public Map<String, IndustryActivity> byName() {
		Map<String, IndustryActivity> ret = name2Activity;
		if (ret == null) {
			return resetCaches(repo.findAll()).byNames;
		}
		return ret;
	}

	public Collection<IndustryActivity> all() {
		return byId().values();
	}

}
