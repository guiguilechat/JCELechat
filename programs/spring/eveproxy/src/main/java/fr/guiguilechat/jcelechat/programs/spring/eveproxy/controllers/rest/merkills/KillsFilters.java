package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.merkills;

import java.util.List;

public record KillsFilters(
		List<Integer> types, String timeSort, AggregPeriod period) {
}