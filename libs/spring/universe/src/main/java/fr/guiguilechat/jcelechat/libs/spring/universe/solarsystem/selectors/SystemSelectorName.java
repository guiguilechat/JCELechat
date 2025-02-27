package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SecFilter;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemRepository;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SystemSelector;
import lombok.RequiredArgsConstructor;

/**
 * select system ids from a region/constellation/sys name and security status
 */
@RequiredArgsConstructor
public enum SystemSelectorName implements SystemSelector<String> {
	rn(SecFilter.ALL) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return repo.idByRegionNameIgnoreCase(name, minSS, maxSS);
		}
	},

	rnhs(SecFilter.HS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return rn.listIds(repo, name, minSS, maxSS);
		}
	},

	rnls(SecFilter.LS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return rn.listIds(repo, name, minSS, maxSS);
		}
	},

	rnns(SecFilter.NS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return rn.listIds(repo, name, minSS, maxSS);
		}
	},

	cn(SecFilter.ALL) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return repo.idByConstellationNameIgnoreCase(name, minSS, maxSS);
		}
	},

	cnhs(SecFilter.HS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return cn.listIds(repo, name, minSS, maxSS);
		}
	},

	cnls(SecFilter.LS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return cn.listIds(repo, name, minSS, maxSS);
		}
	},

	cnns(SecFilter.NS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return cn.listIds(repo, name, minSS, maxSS);
		}
	},

	sn(SecFilter.ALL) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS) {
			return repo.findByNameEqualsIgnoreCase(name).stream()
					.filter(ss -> ss.getSecurityStatus() >= minSS && ss.getSecurityStatus() <= maxSS)
					.map(SolarSystem::getId)
					.toList();
		}

		@Override
		public List<Integer> apply(SolarSystemRepository repo, String name) {
			return repo.findByNameEqualsIgnoreCase(name).stream()
					.map(SolarSystem::getId)
					.toList();
		}

	},

	;

	private final SecFilter sec;

	/**
	 * lookup potential systems in db
	 */
	protected abstract List<Integer> listIds(SolarSystemRepository repo, String name, float minSS, float maxSS);

	@Override
	public List<Integer> apply(SolarSystemRepository repo, String name) {
		return listIds(repo, name, sec.lowerSS, sec.higherSS);
	}
}