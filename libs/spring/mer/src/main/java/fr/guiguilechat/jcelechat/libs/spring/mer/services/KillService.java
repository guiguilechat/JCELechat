package fr.guiguilechat.jcelechat.libs.spring.mer.services;

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

	public static record MonthlyStats(Instant month, long nbKills, double totalIskLost, double medianIskLost) {

		public MonthlyStats(Object[] line) {
			this((Instant) line[0],
					(long) line[1],
					(double) line[2],
					(double) line[3]);
		}

	}

	public List<MonthlyStats> monthlyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.monthlyKills(destroyedShipTypeId).stream().map(MonthlyStats::new).toList();
	}

}
