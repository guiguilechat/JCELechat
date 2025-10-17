package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.Station;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NpcCharacterService extends SdeEntityService<NpcCharacter, Integer, NpcCharacterRepository> {

	@Override
	protected NpcCharacter create(Integer entityId) {
		var ret = new NpcCharacter();
		ret.setId(entityId);
		return ret;
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
