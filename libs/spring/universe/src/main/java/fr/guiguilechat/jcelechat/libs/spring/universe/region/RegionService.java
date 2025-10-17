package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class RegionService extends SdeEntityService<Region, Integer, RegionRepository> {

	public RegionService() {
		super(Region::new);
	}

	//
	// usage
	//

	public Map<Integer, String> namesById() {
		return repo().findAll().stream().collect(Collectors.toMap(Region::getId, Region::name));
	}

	public List<Region> byName(String name) {
		return repo().findByNameEqualsIgnoreCase(name);
	}

	public List<Region> byUniverse(String universe) {
		return repo().findByUniverse(universe);
	}

	public List<String> listUniverses() {
		return repo().listUniverses();
	}

	public List<Integer> listIdsByUniverse(String universe) {
		return repo().listIdsByUniverse(universe);
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

}
