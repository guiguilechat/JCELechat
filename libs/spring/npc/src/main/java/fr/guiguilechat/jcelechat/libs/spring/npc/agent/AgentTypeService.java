package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class AgentTypeService extends SdeEntityService<AgentType, Integer, AgentTypeRepository> {

	@Override
	public AgentType create(Integer entityId) {
		var ret = new AgentType();
		ret.setId(entityId);
		return ret;
	}

}
