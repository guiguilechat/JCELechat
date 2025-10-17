package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AgentTypeService extends SdeEntityService<AgentType, Integer, AgentTypeRepository> {

	@Override
	protected AgentType create(Integer entityId) {
		var ret = new AgentType();
		ret.setId(entityId);
		return ret;
	}

}
