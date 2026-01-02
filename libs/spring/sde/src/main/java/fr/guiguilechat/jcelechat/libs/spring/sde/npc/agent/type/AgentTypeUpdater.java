package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.type;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EagentTypes;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;

@Service
public class AgentTypeUpdater extends SdeEntityUpdater<AgentType, AgentTypeRepository, AgentTypeService, EagentTypes> {

	public AgentTypeUpdater() {
		super(EagentTypes.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EagentTypes> sources) {
		var storedEntities = new HashMap<>(repo().mapAllById());
		for (var e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue());
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
