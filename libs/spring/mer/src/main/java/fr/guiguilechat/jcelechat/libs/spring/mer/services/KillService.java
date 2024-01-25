package fr.guiguilechat.jcelechat.libs.spring.mer.services;

import java.sql.Timestamp;
import java.time.Instant;
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

	public static record MonthlyStats(Instant month, long nbKills, double totalIskLost, double medianIskLost,
			double minIskLost) {

		public MonthlyStats(Object[] line) {
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
			return null;
		}

	}

	public List<MonthlyStats> monthlyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.monthlyKills(destroyedShipTypeId).stream().map(MonthlyStats::new).toList();
	}

}
