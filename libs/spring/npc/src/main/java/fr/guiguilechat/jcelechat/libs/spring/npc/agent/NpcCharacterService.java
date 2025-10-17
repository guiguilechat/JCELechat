package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.Station;

@Service
public class NpcCharacterService extends SdeEntityService<NpcCharacter, Integer, NpcCharacterRepository> {

	public NpcCharacterService() {
		super(NpcCharacter::new);
	}

	public static record AgentDetails(NpcCharacter agent, Station station, SolarSystem solarSystem,
			Constellation constellation, Region region, CorporationInfo corporation) {
	}

	public List<AgentDetails> agentDetails() {
		return repo().listFullAgents().stream().map(arr -> new AgentDetails((NpcCharacter) arr[0], (Station) arr[1],
				(SolarSystem) arr[2], (Constellation) arr[3], (Region) arr[4], (CorporationInfo) arr[5]))
				.toList();
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

}
