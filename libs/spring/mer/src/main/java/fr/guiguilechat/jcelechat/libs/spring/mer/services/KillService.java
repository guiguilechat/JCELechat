package fr.guiguilechat.jcelechat.libs.spring.mer.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.mer.model.Kill;
import fr.guiguilechat.jcelechat.libs.spring.mer.repositories.KillRepository;

@Service
public class KillService {

	@Autowired
	private KillRepository repo;

	public void saveAll(Iterable<Kill> entities) {
		repo.saveAll(entities);
	}

	public Kill save(Kill entity) {
		return repo.save(entity);
	}

	public static record KillStats(Instant periodStart, long nbKills, double totalIskLost, double medianIskLost,
			double minIskLost) {

		public KillStats(Object[] line) {
			this(castInstant(line[0]),
					(long) line[1],
					(double) line[2],
					(double) line[3],
					(double) line[4]);
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

	public List<KillStats> monthlyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.monthlyKills(destroyedShipTypeId).stream().map(KillStats::new).toList();
	}

	public List<KillStats> weeklyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.weeklyKills(destroyedShipTypeId).stream().map(KillStats::new).toList();
	}

	public List<KillStats> yearlyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.yearlyKills(destroyedShipTypeId).stream().map(KillStats::new).toList();
	}

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

}
