package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository repo;

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
}
