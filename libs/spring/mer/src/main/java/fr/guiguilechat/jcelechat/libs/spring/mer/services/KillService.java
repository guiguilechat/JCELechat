package fr.guiguilechat.jcelechat.libs.spring.mer.services;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

	public static record MonthlyStats(String month, long nbKills, double totalMIskLost, double medianMIskLost,
			double averageMIskLost) {

		static final DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM");

		public MonthlyStats(Object[] line) {
			this(format.format(((Instant) line[0]).atOffset(ZoneOffset.UTC)),
					(long) line[1],
					(double) line[2] / 1000000,
					(double) line[3] / 1000000,
					(double) line[2] / 1000000 / (long) line[1]);
		}

	}

	public List<MonthlyStats> monthlyStats(Collection<Integer> destroyedShipTypeId) {
		return repo.monthlyKills(destroyedShipTypeId).stream().map(MonthlyStats::new).toList();
	}

}
