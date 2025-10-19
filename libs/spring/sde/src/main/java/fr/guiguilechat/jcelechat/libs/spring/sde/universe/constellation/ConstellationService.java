package fr.guiguilechat.jcelechat.libs.spring.sde.universe.constellation;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class ConstellationService
		extends SdeEntityService<Constellation, Integer, ConstellationRepository> {

	public ConstellationService() {
		super(Constellation::new);
	}

	//
	// usage
	//

	public List<Integer> listIdsByUniverse(String universe) {
		return repo().listIdsByUniverse(universe);
	}

	public List<Integer> listIdsByRegionId(int regionId) {
		return repo().listIdsByRegionId(regionId);
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

}
