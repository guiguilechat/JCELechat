package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class AgentTypeService extends SdeEntityService<AgentType, Integer, AgentTypeRepository> {

	public AgentTypeService() {
		super(AgentType::new);
	}

}
