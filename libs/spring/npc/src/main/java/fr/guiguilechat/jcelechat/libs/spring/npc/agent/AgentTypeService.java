package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AgentTypeService {

	@Lazy
	private final AgentTypeRepository repo;

	public Map<Integer, AgentType> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(AgentType::getId, o -> o));
	}

	public List<AgentType> saveAll(Iterable<AgentType> entities) {
		return repo.saveAllAndFlush(entities);
	}

}
