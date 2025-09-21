package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.npc.agent.Agent.AgentDivision;
import fr.guiguilechat.jcelechat.libs.spring.npc.agent.Agent.AgentType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.invnames.NamesIndex;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eagents;
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

	@Lazy
	private final AgentService agentService;

	@Lazy
	private final NamesIndex namesIndex;

	private LinkedHashMap<Integer, Eagents> agents = null;

	@Override
	public void beforeSdeUpdate() {
		agents = null;
		agentService.clear();
	}

	static final Pattern ENTRYNAME_AGENTS_PATTERN = Pattern.compile(
			"fsd/agents\\.yaml");

	@Override
	public void onSdeFile(String name, Supplier<InputStream> fileContent) {
		if (ENTRYNAME_AGENTS_PATTERN.matcher(name).matches()) {
			agents = Eagents.LOADER.from(fileContent.get());
			return;
		}
	}

	@Override
	public void afterSdeUpdate() {
		if (agents == null) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file for matcher "
					+ ENTRYNAME_AGENTS_PATTERN);
		} else {
			Map<Integer, String> nameIndex = namesIndex.getIndex();
			List<Agent> newAgents = agents.entrySet().stream()
					.map(e -> convert(e.getKey(), e.getValue(), nameIndex))
					.toList();
			agentService.saveAll(newAgents);
			log.info("udpated {} agents", newAgents.size());
		}
	}

	protected Agent convert(int id, Eagents entry, Map<Integer, String> nameIndex) {
		String name = nameIndex == null ? null : nameIndex.get(id);
		return Agent.builder()
				.agentDivision(AgentDivision.of(entry.divisionID))
				.agentType(AgentType.of(entry.agentTypeID))
				.corporationId(entry.corporationID)
				.divisionId(entry.divisionID)
				.id(id)
				.isLocator(entry.isLocator)
				.level(entry.level)
				.locationId(entry.locationID)
				.name(name)
				.build();
	}

}
