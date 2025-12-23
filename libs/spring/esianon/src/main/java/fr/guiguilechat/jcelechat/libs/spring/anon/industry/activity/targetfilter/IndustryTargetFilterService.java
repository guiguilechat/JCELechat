package fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.targetfilter;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndustryTargetFilterService {

	final private IndustryTargetFilterRepository repo;

	public Map<Integer, IndustryTargetFilter> byId(Iterable<Integer> ids) {
		return repo.findAllById(ids).stream()
				.collect(Collectors.toMap(IndustryTargetFilter::getId, itf -> itf));
	}

	public Map<Integer, IndustryTargetFilter> all() {
		return repo.findAll().stream()
				.collect(Collectors.toMap(IndustryTargetFilter::getId, itf -> itf));
	}

}
