package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters.AgentData;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * the actual storage is done in post process since it needs the inv names to
 * deduce the agents names
 */
@Service
public class NpcCharacterUpdater extends SdeEntityUpdater<NpcCharacter, NpcCharacterService, EnpcCharacters> {

	public NpcCharacterUpdater() {
		super(EnpcCharacters.SDE_FILE_YAML, EnpcCharacters.LOADER);

	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private AgentTypeService agentTypeService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private DivisionService divisionService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EnpcCharacters> sources) {
		Map<Integer, AgentType> agentTypes = new HashMap<>(agentTypeService.allById());
		Map<Integer, Division> divisions = new HashMap<>(divisionService.allById());
		Map<Integer, NpcCharacter> storedAgents = new HashMap<>(service().allById());

		for (Entry<Integer, EnpcCharacters> e : sources.entrySet()) {
			var stored = storedAgents.computeIfAbsent(e.getKey(), service()::create);
			update(stored, e.getValue(), agentTypes, divisions);
		}

		agentTypeService.saveAll(agentTypes.values());
		divisionService.saveAll(divisions.values());
		service().saveAll(storedAgents.values());
	}

	protected void update(NpcCharacter entity,
			EnpcCharacters entry,
			Map<Integer, AgentType> agentTypes,
			Map<Integer, Division> divisions) {
		entity.receivedSource();
		AgentData data = entry.agent;
		if (data != null) {
			entity.setAgentType(agentTypes.computeIfAbsent(data.agentTypeID, agentTypeService::create));
			entity.setDivision(divisions.computeIfAbsent(data.divisionID, divisionService::create));
			entity.setLocator(data.isLocator);
			entity.setLevel(data.level);
		} else {
			entity.setAgentType(null);
			entity.setDivision(null);
			entity.setLocator(false);
			entity.setLevel(0);
		}
		entity.setCorporationId(entry.corporationID);
		entity.setLocationId(entry.locationID);
		entity.setName(entry.enName());
	}

}
