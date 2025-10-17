package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors.SystemSelectorId;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors.SystemSelectorName;

@Service
public class SolarSystemService extends
		SdeEntityService<SolarSystem, Integer, SolarSystemRepository> {

	@Override
	public SolarSystem create(Integer entityId) {
		SolarSystem ret = new SolarSystem();
		ret.setId(entityId);
		return ret;
	}

	//
	// usage
	//

	/**
	 * @param solar system
	 * @return all the solar systems of the destination of the stargates of the
	 *           given source system
	 */
	public List<SolarSystem> adjacent(SolarSystem source) {
		return repo().adjacent(source);
	}

	public List<Integer> adjacentIds(int solarSystemId) {
		return repo().adjacentIds(solarSystemId);
	}

	public Map<Integer, String> namesForIds(Iterable<Integer> systemIds) {
		return repo().findAllById(systemIds).stream().collect(Collectors.toMap(SolarSystem::getId, SolarSystem::name));
	}

	public List<Integer> selectNames(SystemSelectorName ssn, Iterable<String> names) {
		return ssn.apply(repo(), names);
	}

	public List<Integer> selectIds(SystemSelectorId ssi, Iterable<Integer> ids) {
		return ssi.apply(repo(), ids);
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

	public List<Integer> listIdsBySecurityBetween(float minSec, float maxSec) {
		return repo().listIdsBySecurityBetween(minSec, maxSec);
	}

	public List<Integer> listIdsByUniverse(String universe) {
		return repo().listIdsByUniverse(universe);
	}

	public List<Integer> listIdsByRegionId(int regionId) {
		return repo().listIdsByRegionId(regionId);
	}

	public List<Integer> listIdsByConstellationId(int constellationId) {
		return repo().listIdsByConstellationId(constellationId);
	}

}
