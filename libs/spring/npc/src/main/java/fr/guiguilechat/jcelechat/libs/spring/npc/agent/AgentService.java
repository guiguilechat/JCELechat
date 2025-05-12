package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.Station;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AgentService {

	final private AgentRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Agent> saveAll(Iterable<Agent> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public Agent save(Agent entity) {
		return repo.saveAndFlush(entity);
	}

	public static record AgentDetails(Agent agent, Station station, SolarSystem solarSystem,
			Constellation constellation, Region region, CorporationInfo corporation) {
	}

	public List<AgentDetails> agentDetails() {
		return repo.listFullAgents().stream().map(arr -> new AgentDetails((Agent) arr[0], (Station) arr[1],
				(SolarSystem) arr[2], (Constellation) arr[3], (Region) arr[4], (CorporationInfo) arr[5]))
				.toList();
	}

	public List<Integer> listIds() {
		return repo.listIds();
	}

}
