package fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillRepository;
import fr.guiguilechat.jcelechat.libs.spring.mer.updater.MerUpdateService.MerUpdateListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KillStatsService implements MerUpdateListener {

	final private KillRepository repo;

	@Cacheable("merKillsStats")
	public List<KillStats> stats(KillsAggregation aggreg, Collection<Integer> destroyedShipTypeId) {
		return switch (aggreg) {
		case DAYLY -> repo.dailyKills(destroyedShipTypeId);
		case MONTHLY -> repo.monthlyKills(destroyedShipTypeId);
		case WEEKLY -> repo.weeklyKills(destroyedShipTypeId);
		case YEARLY -> repo.yearlyKills(destroyedShipTypeId);
		default -> throw new UnsupportedOperationException("case " + aggreg + " not handled");
		};
	}

	// caching

	/** list of caches to invalidate */
	static final List<String> MER_KILLS_CACHES = List.of(
			"merKillsStats");

	@Override
	public List<String> listMerCaches() {
		return MER_KILLS_CACHES;
	}

}
