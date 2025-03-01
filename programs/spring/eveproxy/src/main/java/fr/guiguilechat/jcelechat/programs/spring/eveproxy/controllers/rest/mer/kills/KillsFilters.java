package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills;

import java.util.List;

public record KillsFilters(
		List<Integer> types, String timeSort, AggregPeriod period) {
}