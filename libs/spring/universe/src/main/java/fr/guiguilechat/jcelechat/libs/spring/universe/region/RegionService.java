package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RegionService {

	@Getter(value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	@Lazy
	private final RegionRepository repo;

	public Map<Integer, Region> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(Region::getId, o -> o));
	}

	public List<Region> saveAll(Iterable<Region> entities) {
		return repo.saveAllAndFlush(entities);
	}

	//
	// usage
	//

	public Region byId(int id) {
		return repo().findById(id).orElse(null);
	}

	public Region createIfAbsent(int id) {
		var op = repo().findById(id);
		if (op.isEmpty()) {
			Region ret = Region.builder().id(id).build();
			repo().save(ret);
			return ret;
		} else {
			return op.get();
		}
	}

	public Map<Integer, Region> createIfAbsent(Collection<Integer> ids) {
		var l = repo().findAllById(ids);
		if (l.size() == ids.size()) {
			return l.stream().collect(Collectors.toMap(Region::getId, o -> o));
		}
		Map<Integer, Region> m = l.stream().collect(Collectors.toMap(Region::getId, o -> o));
		Map<Integer, Region> ret = new HashMap<>();
		List<Region> created = new ArrayList<>();
		for (int id : ids) {
			Region added = m.get(id);
			if (added == null) {
				added = Region.builder().id(id).build();
				created.add(added);
			}
			ret.put(id, added);
		}
		repo().saveAll(created);
		return ret;
	}

	public Collection<Region> findById(Iterable<Integer> regionIds) {
		return repo().findAllById(regionIds);
	}

	public Map<Integer, String> namesById() {
		return repo().findAll().stream().collect(Collectors.toMap(Region::getId, Region::name));
	}

	public List<Region> byName(String name) {
		return repo().findByNameEqualsIgnoreCase(name);
	}

	public List<Region> byUniverse(String universe) {
		return repo().findByUniverse(universe);
	}

	public List<String> listUniverses() {
		return repo().listUniverses();
	}

	public List<Integer> listIdsByUniverse(String universe) {
		return repo().listIdsByUniverse(universe);
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

}
