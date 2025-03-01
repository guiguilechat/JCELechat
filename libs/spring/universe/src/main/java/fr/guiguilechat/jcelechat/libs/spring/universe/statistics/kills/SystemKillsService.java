package fr.guiguilechat.jcelechat.libs.spring.universe.statistics.kills;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.DateAggregation;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemDateActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.kills.SystemKills.SystemKillsFetch;
import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetchService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_kills;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.statskills")
@Order(4) // depends on solar systems
public class SystemKillsService extends
    BatchFetchService<SystemKillsFetch, SystemKillsFetchRepository, SystemKills, SystemKillsRepository, R_get_universe_system_kills[]> {

	private final SolarSystemService solarSystemService;

	@Override
	protected Requested<R_get_universe_system_kills[]> fetchData(Map<String, String> properties) {
		Requested<R_get_universe_system_kills[]> ret = ESIRawPublic.INSTANCE
		    .get_universe_system_kills(properties);
		return ret;
	}

	@Override
	protected SystemKillsFetch createFetch() {
		return new SystemKillsFetch();
	}

	@Override
	protected List<SystemKills> convert(SystemKillsFetch fetch, R_get_universe_system_kills[] response) {
		Map<Integer, SolarSystem> idToSolarSystem = solarSystemService
		    .createIfAbsent(Stream.of(response).map(r -> r.system_id).distinct().toList());
		return Stream.of(response).map(r -> {
			var ret = new SystemKills().update(r);
			ret.setSolarSystem(Objects.requireNonNull(idToSolarSystem.get(r.system_id)));
			return ret;
		}).toList();
	}

	//
	// usage
	//

	public List<SystemDateActivity> aggregateNpcKills(
			Iterable<Integer> systemIds,
			DateAggregation aggregation,
			Instant since) {
		List<Object[]> rows = switch (aggregation) {
		case DateAggregation.hourly -> itemRepository().aggregateNpcKillsHourly(systemIds, since);
		case DateAggregation.daily -> itemRepository().aggregateNpcKillsDaily(systemIds, since);
		case DateAggregation.weekly -> itemRepository().aggregateNpcKillsWeekly(systemIds, since);
		case DateAggregation.monthly -> itemRepository().aggregateNpcKillsMonthly(systemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggregation);
		};
		return rows
				.stream()
				.map(aggregation::ActivityOfRow)
				.toList();
	}

	public List<SystemDateActivity> aggregatePodKills(
			Iterable<Integer> systemIds,
			DateAggregation aggregation,
			Instant since) {
		List<Object[]> rows = switch (aggregation) {
		case DateAggregation.hourly -> itemRepository().aggregatePodKillsHourly(systemIds, since);
		case DateAggregation.daily -> itemRepository().aggregatePodKillsDaily(systemIds, since);
		case DateAggregation.weekly -> itemRepository().aggregatePodKillsWeekly(systemIds, since);
		case DateAggregation.monthly -> itemRepository().aggregatePodKillsMonthly(systemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggregation);
		};
		return rows
				.stream()
				.map(aggregation::ActivityOfRow)
				.toList();
	}

	public List<SystemDateActivity> aggregateShipKills(
			Iterable<Integer> systemIds,
			DateAggregation aggregation,
			Instant since) {
		List<Object[]> rows = switch (aggregation) {
		case DateAggregation.hourly -> itemRepository().aggregateShipKillsHourly(systemIds, since);
		case DateAggregation.daily -> itemRepository().aggregateShipKillsDaily(systemIds, since);
		case DateAggregation.weekly -> itemRepository().aggregateShipKillsWeekly(systemIds, since);
		case DateAggregation.monthly -> itemRepository().aggregateShipKillsMonthly(systemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggregation);
		};
		return rows
				.stream()
				.map(aggregation::ActivityOfRow)
				.toList();
	}

}
