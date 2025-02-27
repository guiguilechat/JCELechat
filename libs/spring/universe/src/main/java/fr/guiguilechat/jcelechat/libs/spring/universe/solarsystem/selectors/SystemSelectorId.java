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
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return repo.idByRegionId(id, minSS, maxSS);
	}

	},

	rihs(SecFilter.HS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return ri.listIds(repo, id, minSS, maxSS);
	}

	},

	rils(SecFilter.LS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return ri.listIds(repo, id, minSS, maxSS);
	}

	},

	rins(SecFilter.NS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return ri.listIds(repo, id, minSS, maxSS);
	}

	},

	ci(SecFilter.ALL) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return repo.idByConstellationId(id, minSS, maxSS);
	}

	},

	cihs(SecFilter.HS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return ci.listIds(repo, id, minSS, maxSS);
	}

	},

	cils(SecFilter.LS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return ci.listIds(repo, id, minSS, maxSS);
	}

	},

	cins(SecFilter.NS) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return ci.listIds(repo, id, minSS, maxSS);
	}

	},

	si(SecFilter.ALL) {

	@Override
		protected List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS) {
			return repo.findById(id).stream()
				.filter(ss -> ss.getSecurityStatus() >= minSS && ss.getSecurityStatus() <= maxSS)
				.map(SolarSystem::getId)
				.toList();
	}

	@Override
		public List<Integer> apply(SolarSystemRepository repo, Integer id) {
			return repo.findById(id).stream()
				.map(SolarSystem::getId)
				.toList();
		}
	},

	;

	private final SecFilter sec;

	/**
	 * lookup potential systems in db
	 */
	protected abstract List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS);

	@Override
	public List<Integer> apply(SolarSystemRepository repo, Integer id) {
		return listIds(repo, id, sec.lowerSS, sec.higherSS);
	}

}
