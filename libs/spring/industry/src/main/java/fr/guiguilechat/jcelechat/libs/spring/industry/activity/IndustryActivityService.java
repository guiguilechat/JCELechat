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
import fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata.EindustryActivities;
import fr.guiguilechat.jcelechat.libs.spring.gameclient.updater.GameClientUpdateListener;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Order(1)
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

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void onGameClientUpdate(ClientCache cache) {
		List<KeyValTime<EindustryActivities>> loaded = EindustryActivities.getLoader().load(cache);
		List<IndustryActivity> translated = loaded.stream().map(l -> IndustryActivity.builder()
				.activityId(l.getVal().activityID)
				.activityName(l.getVal().activityName)
				.description(l.getVal().description)
				.build())
				.toList();
		saveAll(translated);
		resetCaches(translated);
	}

	private Map<Integer, IndustryActivity> id2Activity = null;
	private Map<String, IndustryActivity> name2Activity = null;

	protected record Caches(
			Map<Integer, IndustryActivity> byIds,
			Map<String, IndustryActivity> byNames) {
	}

	protected Caches resetCaches(List<IndustryActivity> data) {
		Map<Integer, IndustryActivity> ids = id2Activity = data.stream()
				.collect(Collectors.toMap(IndustryActivity::getActivityId, a -> a));
		Map<String, IndustryActivity> names = name2Activity = data.stream()
				.collect(Collectors.toMap(IndustryActivity::getActivityName, a -> a));
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
