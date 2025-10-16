package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EagentTypes;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporationDivisions;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * the actual storage is done in post process since it needs the inv names to
 * deduce the agents names
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class AgentUpdaterService implements SdeUpdateListener {

	private final AgentService agentService;

	private final AgentTypeService agentTypeService;

	private final DivisionService divisionService;

	private boolean receivedAgents = false;

	private boolean receivedAgentTypes = false;

	private boolean receivedDivisions = false;

	@Override
	public void beforeSdeUpdate() {
		receivedAgents = false;
		receivedAgentTypes = false;
		receivedDivisions = false;
	}

	@Override
	public void onSdeFile(String name, Supplier<InputStream> fileContent) {
		switch (name) {
		case EnpcCharacters.SDE_FILE_YAML:
			processAgents(EnpcCharacters.LOADER.from(fileContent.get()));
			receivedAgents = true;
			break;
		case EagentTypes.SDE_FILE_YAML:
			processAgentTypes(EagentTypes.LOADER.from(fileContent.get()));
			receivedAgentTypes = true;
			break;
		case EnpcCorporationDivisions.SDE_FILE_YAML:
			processDivisions(EnpcCorporationDivisions.LOADER.from(fileContent.get()));
			receivedDivisions = true;
			break;
		}
	}

	protected void processAgents(LinkedHashMap<Integer, EnpcCharacters> from) {
		Map<Integer, AgentType> agentTypes = new HashMap<>(agentTypeService.allById());
		Map<Integer, Division> divisions = new HashMap<>(divisionService.allById());
		Map<Integer, Agent> storedAgents = new HashMap<>(agentService.allById());
		storedAgents.values().forEach(agent -> agent.setRemoved(true));

		for (Entry<Integer, EnpcCharacters> e : from.entrySet()) {
			var stored = storedAgents.computeIfAbsent(e.getKey(), i -> Agent.builder().id(i).build());
			stored.update(e.getValue(), agentTypes, divisions);
		}

		agentTypeService.saveAll(agentTypes.values());
		divisionService.saveAll(divisions.values());
		agentService.saveAll(storedAgents.values());
		log.info("updated {} agents", storedAgents.size());
	}

	protected void processAgentTypes(LinkedHashMap<Integer, EagentTypes> from) {
		var storedEntities = new HashMap<>(agentTypeService.allById());
		storedEntities.values().forEach(o -> o.setRemoved(true));

		for (var e : from.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), i -> AgentType.builder().id(i).build());
			stored.update(e.getValue());
		}
		agentTypeService.saveAll(storedEntities.values());
	}

	protected void processDivisions(LinkedHashMap<Integer, EnpcCorporationDivisions> from) {
		var storedEntities = new HashMap<>(divisionService.allById());
		storedEntities.values().forEach(o -> o.setRemoved(true));

		for (var e : from.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), i -> Division.builder().id(i).build());
			stored.update(e.getValue());
		}
		divisionService.saveAll(storedEntities.values());
	}

	@Override
	public void afterSdeUpdate() {
		if (!receivedAgents) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file "
					+ EnpcCharacters.SDE_FILE_YAML);
		}
		if (!receivedAgentTypes) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file "
					+ EagentTypes.SDE_FILE_YAML);
		}
		if (!receivedDivisions) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file "
					+ EnpcCorporationDivisions.SDE_FILE_YAML);
		}
	}

}
