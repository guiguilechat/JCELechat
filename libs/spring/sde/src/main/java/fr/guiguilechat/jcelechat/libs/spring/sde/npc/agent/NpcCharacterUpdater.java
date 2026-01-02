package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.division.DivisionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.type.AgentTypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * the actual storage is done in post process since it needs the inv names to
 * deduce the agents names
 */
@Service
public class NpcCharacterUpdater
		extends SdeEntityUpdater<NpcCharacter, NpcCharacterRepository, NpcCharacterService, EnpcCharacters> {

	public NpcCharacterUpdater() {
		super(EnpcCharacters.LOADER);

	}

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private AgentTypeService agentTypeService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private DivisionService divisionService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private NpcCorporationService npcCorpService;

	@Override
	protected void processSource(LinkedHashMap<Integer, EnpcCharacters> sources) {
		var getDivision = divisionService().getterAll();
		var getNpcCorp = npcCorpService.getterAll();
		var getType = agentTypeService().getterAll();
		var storedEntities = new HashMap<>(repo().mapAllById());

		for (Entry<Integer, EnpcCharacters> e : sources.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), service()::create);
			stored.update(e.getValue(), getType, getDivision, getNpcCorp);
		}
		repo().saveAllAndFlush(storedEntities.values());
	}

}
