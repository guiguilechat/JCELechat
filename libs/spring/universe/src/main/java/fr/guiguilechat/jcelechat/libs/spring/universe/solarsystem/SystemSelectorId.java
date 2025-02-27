package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.List;

import lombok.RequiredArgsConstructor;

/**
 * select system ids from an id
 */
@RequiredArgsConstructor
public enum SystemSelectorId {
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
	public List<Integer> apply(SolarSystemRepository repo, int id) {
			return repo.findById(id).stream()
				.map(SolarSystem::getId)
				.toList();
	}};

	private final SecFilter sec;

	/**
	 * lookup potential systems in db
	 */
	protected abstract List<Integer> listIds(SolarSystemRepository repo, int id, float minSS, float maxSS);

	public List<Integer> apply(SolarSystemRepository repo, int id) {
		return listIds(repo, id, sec.lowerSS, sec.higherSS);
	}

}
