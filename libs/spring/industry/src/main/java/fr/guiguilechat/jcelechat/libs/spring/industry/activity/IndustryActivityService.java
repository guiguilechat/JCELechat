package fr.guiguilechat.jcelechat.libs.spring.industry.activity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndustryActivityService {

	final private IndustryActivityRepository repo;

	private Map<Integer, IndustryActivity> id2Activity = null;
	private Map<String, IndustryActivity> name2Activity = null;

	protected record Caches(
			Map<Integer, IndustryActivity> byIds,
			Map<String, IndustryActivity> byNames) {
	}

	Caches resetCaches(List<IndustryActivity> data) {
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
