package fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps;

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
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.DateActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.aggregate.DateAggregation;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.aggregate.PeriodHeat;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.aggregate.SystemDateActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps.SystemJumps.SystemJumpsFetch;
import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetchService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_jumps;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.statsjumps")
@Order(4) // depends on solar systems
public class SystemJumpsService extends
BatchFetchService<SystemJumpsFetch, SystemJumpsFetchRepository, SystemJumps, SystemJumpsRepository, R_get_universe_system_jumps[]> {

	private final SolarSystemService solarSystemService;

	@Override
	protected Requested<R_get_universe_system_jumps[]> fetchData(Map<String, String> properties) {
		Requested<R_get_universe_system_jumps[]> ret = ESIRawPublic.INSTANCE
				.get_universe_system_jumps(properties);
		return ret;
	}

	@Override
	protected SystemJumpsFetch createFetch() {
		return new SystemJumpsFetch();
	}

	@Override
	protected List<SystemJumps> convert(SystemJumpsFetch fetch, R_get_universe_system_jumps[] response) {
		Map<Integer, SolarSystem> idToSolarSystem = solarSystemService
				.createIfAbsent(Stream.of(response).map(r -> r.system_id).distinct().toList());
		return Stream.of(response).map(r -> {
			var ret = new SystemJumps().update(r);
			ret.setSolarSystem(Objects.requireNonNull(idToSolarSystem.get(r.system_id)));
			return ret;
		}).toList();
	}

	//
	// usage
	//

	/**
	 * @return the stored time-jumps of given system
	 */
	public List<DateActivity> listJumps(
			int systemId,
			Instant since) {
		List<Object[]> rows = itemRepository().jumpsForSystemId(systemId, since);
		return rows
				.stream()
				.map(DateActivity::ofRow)
				.toList();
	}

	/**
	 * @return the aggregated jumps of several system ids
	 */
	public List<SystemDateActivity> aggregateJumps(
			Iterable<Integer> systemIds,
			DateAggregation aggregation,
			Instant since) {
		List<Object[]> rows = switch (aggregation) {
		case DateAggregation.hourly -> itemRepository().aggregateJumpsHourly(systemIds, since);
		case DateAggregation.daily -> itemRepository().aggregateJumpsDaily(systemIds, since);
		case DateAggregation.weekly -> itemRepository().aggregateJumpsWeekly(systemIds, since);
		case DateAggregation.monthly -> itemRepository().aggregateJumpsMonthly(systemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggregation);
		};
		return rows
				.stream()
				.map(aggregation::ActivityOfRow)
				.toList();
	}

	/**
	 * converts a row from {@link SystemJumpsRepository#activity(Iterable, int)}
	 * into a
	 * {@link PeriodHeat}
	 *
	 * @param line
	 * @return
	 */
	static PeriodHeat wActivity(Object[] line) {
		return PeriodHeat.builder()
				.ssId(((Number) line[0]).intValue())
				.dow(((Number) line[1]).intValue())
				.h(((Number) line[2]).intValue())
				.dailyJumps(((Number) line[3]).doubleValue()).build();
	}

	public List<PeriodHeat> wActivity(Iterable<Integer> systemIds, int weeks) {
		return itemRepository().activity(systemIds, weeks).stream().map(SystemJumpsService::wActivity).toList();
	}

}
