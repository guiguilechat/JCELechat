package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SecFilter;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemRepository;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SystemSelector;
import lombok.RequiredArgsConstructor;

/**
 * select system ids from a region/constellation/sys id and security status
 */
@RequiredArgsConstructor
public enum SystemSelectorId implements SystemSelector<Integer> {
	ri(SecFilter.ALL) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return repo.idByRegionIdIn(ids, minSS, maxSS);
	}

	},

	rihs(SecFilter.HS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return ri.listIds(repo, ids, minSS, maxSS);
	}

	},

	rils(SecFilter.LS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return ri.listIds(repo, ids, minSS, maxSS);
	}

	},

	rins(SecFilter.NS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return ri.listIds(repo, ids, minSS, maxSS);
	}

	},

	ci(SecFilter.ALL) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return repo.idByConstellationIdIn(ids, minSS, maxSS);
	}

	},

	cihs(SecFilter.HS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return ci.listIds(repo, ids, minSS, maxSS);
	}

	},

	cils(SecFilter.LS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return ci.listIds(repo, ids, minSS, maxSS);
	}

	},

	cins(SecFilter.NS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return ci.listIds(repo, ids, minSS, maxSS);
	}

	},

	si(SecFilter.ALL) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS, float maxSS) {
			return repo.findAllById(ids).stream()
					.filter(ss -> ss.getSecurityStatus().floatValue() >= minSS
							&& ss.getSecurityStatus().floatValue() <= maxSS)
				.map(SolarSystem::getId)
				.toList();
	}

	@Override
		public List<Integer> apply(SolarSystemRepository repo, Iterable<Integer> ids) {
			return repo.findAllById(ids).stream()
				.map(SolarSystem::getId)
				.toList();
		}
	},

	;

	private final SecFilter sec;

	/**
	 * lookup potential systems in db
	 */
	protected abstract List<Integer> listIds(SolarSystemRepository repo, Iterable<Integer> ids, float minSS,
			float maxSS);

	@Override
	public List<Integer> apply(SolarSystemRepository repo, Iterable<Integer> ids) {
		return listIds(repo, ids, sec.lowerSS, sec.higherSS);
	}

}
