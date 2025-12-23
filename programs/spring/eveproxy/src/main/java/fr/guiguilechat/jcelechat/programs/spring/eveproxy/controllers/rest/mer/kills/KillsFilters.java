package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillsAggregation;

public record KillsFilters(
		List<Integer> types, String timeSort, KillsAggregation period) {
}