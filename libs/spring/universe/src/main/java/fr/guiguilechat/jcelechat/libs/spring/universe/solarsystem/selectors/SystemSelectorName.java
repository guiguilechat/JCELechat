package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors;

import java.util.List;

import org.springframework.data.util.StreamUtils;

import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SecFilter;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemRepository;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SystemSelector;
import lombok.RequiredArgsConstructor;

/**
 * select system ids from region/constellation/sys names and security status
 */
@RequiredArgsConstructor
public enum SystemSelectorName implements SystemSelector<String> {
	rn(SecFilter.ALL) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return repo.idByLowerRegionNameIn(lowerCaseNames, minSS, maxSS);
		}
	},

	rnhs(SecFilter.HS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return rn.listIds(repo, lowerCaseNames, minSS, maxSS);
		}
	},

	rnls(SecFilter.LS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return rn.listIds(repo, lowerCaseNames, minSS, maxSS);
		}
	},

	rnns(SecFilter.NS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return rn.listIds(repo, lowerCaseNames, minSS, maxSS);
		}
	},

	cn(SecFilter.ALL) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return repo.idByLowerConstellationNameIn(lowerCaseNames, minSS, maxSS);
		}
	},

	cnhs(SecFilter.HS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return cn.listIds(repo, lowerCaseNames, minSS, maxSS);
		}
	},

	cnls(SecFilter.LS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return cn.listIds(repo, lowerCaseNames, minSS, maxSS);
		}
	},

	cnns(SecFilter.NS) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return cn.listIds(repo, lowerCaseNames, minSS, maxSS);
		}
	},

	sn(SecFilter.ALL) {
		@Override
		protected List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
				float maxSS) {
			return repo.idByLowerNameIn(lowerCaseNames, minSS, maxSS);
		}

		@Override
		public List<Integer> apply(SolarSystemRepository repo, Iterable<String> names) {
			return repo.idByLowerNameIn(lowerCases(names), SecFilter.ALL.lowerSS, SecFilter.ALL.higherSS);
		}

	},

	;

	private final SecFilter sec;

	/**
	 * lookup potential systems in db
	 */
	protected abstract List<Integer> listIds(SolarSystemRepository repo, Iterable<String> lowerCaseNames, float minSS,
			float maxSS);

	@Override
	public List<Integer> apply(SolarSystemRepository repo, Iterable<String> name) {
		return listIds(repo, name, sec.lowerSS, sec.higherSS);
	}

	public static List<String> lowerCases(Iterable<String> strings) {
		return StreamUtils.createStreamFromIterator(strings.iterator())
				.map(s -> s == null ? null : s.toLowerCase())
				.toList();
	}
}