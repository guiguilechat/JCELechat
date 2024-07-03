package fr.guiguilechat.jcelechat.libs.spring.mer.kill;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.mer.updater.MerUpdateService.MerUpdateListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KillService implements MerUpdateListener {

	final private KillRepository repo;

	public void saveAll(Iterable<Kill> entities) {
		repo.saveAll(entities);
	}

	public Kill save(Kill entity) {
		return repo.save(entity);
	}

	public static record KillStats(Instant periodStart, long nbKills, double totalIskLost, double medianIskLost,
			double minIskLost) implements Serializable {

		public KillStats(Object[] line) {
			this(castInstant(line[0]),
					((Number) line[1]).longValue(),
					((Number) line[2]).doubleValue(),
					((Number) line[3]).doubleValue(),
					((Number) line[4]).doubleValue());
		}

		static Instant castInstant(Object o) {
			if (o == null) {
				return null;
			}
			if (o instanceof Instant) {
				return (Instant) o;
			}
			if (o instanceof Timestamp) {
				return ((Timestamp) o).toInstant();
			}
			if (o instanceof OffsetDateTime) {
				return ((OffsetDateTime) o).toInstant();
			}
			throw new UnsupportedOperationException("can't cast " + o.getClass().getCanonicalName() + " as Instant");
		}

	}

	@Cacheable("merKillsMonthlyStats")
	public List<KillStats> monthlyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.monthlyKills(destroyedShipTypeId).stream().map(KillStats::new).toList();
	}

	@Cacheable("merKillsWeeklyStats")
	public List<KillStats> weeklyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.weeklyKills(destroyedShipTypeId).stream().map(KillStats::new).toList();
	}

	@Cacheable("merKillsYearlyStats")
	public List<KillStats> yearlyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.yearlyKills(destroyedShipTypeId).stream().map(KillStats::new).toList();
	}

	@Cacheable("merKillsDailyStats")
	public List<KillStats> dailyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.dailyKills(destroyedShipTypeId).stream()
				.map(KillStats::new).toList();
	}

	final static List<Integer> NPCCorporationIds = List.of(
			1000127, // Guristas [G]
			1000132, // Secure Commerce Commission [SCC]
			1000134, // Blood Raiders [TBR]
			1000148, // InterBus [INB]
			1000274 // Vigilant Tyrannos [VI-TY]
	);

	/** list of caches to invalidate */
	static final List<String> MER_KILLS_CACHES = List.of(
			"merKillsMonthlyStats",
			"merKillsWeeklyStats",
			"merKillsYearlyStats",
			"merKillsDailyStats");

	@Override
	public List<String> listMerCaches() {
		return MER_KILLS_CACHES;
	}

}
