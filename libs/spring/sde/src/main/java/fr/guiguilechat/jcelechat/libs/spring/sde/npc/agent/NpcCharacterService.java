package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class NpcCharacterService extends SdeEntityService<NpcCharacter, Integer, NpcCharacterRepository> {

	public NpcCharacterService() {
		super(NpcCharacter::new);
	}

	public static record AgentDetails(NpcCharacter agent, Station station, SolarSystem solarSystem,
			Constellation constellation, Region region, NpcCorporation corporation) {
	}

	public List<AgentDetails> agentDetails() {
		return repo().listFullAgents().stream().map(arr -> new AgentDetails((NpcCharacter) arr[0], (Station) arr[1],
				(SolarSystem) arr[2], (Constellation) arr[3], (Region) arr[4], (NpcCorporation) arr[5]))
				.toList();
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

}
