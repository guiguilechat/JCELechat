package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DivisionService {

	@Lazy
	private final DivisionRepository repo;

	public Map<Integer, Division> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(Division::getId, o -> o));
	}

	public List<Division> saveAll(Iterable<Division> entities) {
		return repo.saveAllAndFlush(entities);
	}

}
