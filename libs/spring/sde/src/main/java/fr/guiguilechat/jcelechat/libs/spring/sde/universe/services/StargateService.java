package fr.guiguilechat.jcelechat.libs.spring.sde.universe.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories.StargateRepository;

@Service
public class StargateService {

	@Autowired
	private StargateRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Stargate> saveAll(Iterable<Stargate> entities) {
		return repo.saveAll(entities);
	}

	public Stargate findById(int stargateId) {
		return repo.findById(stargateId).orElse(null);
	}


	public static final List<String> CACHE_LIST = List.of("SdeUniverseWJD");


	public static record WarpJumpDist(int start, int end, double distance) implements Serializable {

		public static WarpJumpDist of(Object[] arr) {
			return new WarpJumpDist(
					((Stargate) arr[0]).getStargateId(),
					((Stargate) arr[1]).getStargateId(),
					(double) arr[2]
			);
		}
	}

	@Cacheable("SdeUniverseWJD")
	public List<WarpJumpDist> listG2G() {
		return repo.listWarpJump().stream().map(WarpJumpDist::of).toList();
	}

}
