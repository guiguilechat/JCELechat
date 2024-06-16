package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Deprecated
public class GroupService {

	final private GroupRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Group> saveAll(Iterable<Group> entities) {
		return repo.saveAll(entities);
	}

	public Group save(Group entity) {
		return repo.save(entity);
	}

	public Optional<Group> byId(int groupId) {
		return repo.findById(groupId);
	}

	public List<Group> byName(String nameIgnoreCase) {
		return repo.findByNameEqualsIgnoreCase(nameIgnoreCase);
	}

	public List<Group> byCatId(int catId) {
		return repo.findByCategoryCategoryId(catId);
	}

	public Group prevGroup(Group group) {
		return repo.findTop1ByCategoryAndNameLessThanOrderByNameDesc(group.getCategory(), group.getName());
	}

	public Group nextGroup(Group group) {
		return repo.findTop1ByCategoryAndNameGreaterThanOrderByNameAsc(group.getCategory(), group.getName());
	}

	public Map<Integer, Group> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(Group::getGroupId, c -> c));
	}
}
