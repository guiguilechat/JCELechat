package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories.TypeRepository;

@Service
public class TypeService {

	@Autowired
	private TypeRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Type> saveAll(Iterable<Type> entities) {
		return repo.saveAll(entities);
	}

	public Type save(Type entity) {
		return repo.save(entity);
	}

	public List<Type> byGroupId(int groupId) {
		return repo.findByGroupGroupId(groupId);
	}

	public Optional<Type> byId(int typeId) {
		return repo.findById(typeId);
	}

	public List<Type> byName(String nameIgnoreCase) {
		return repo.findByNameEqualsIgnoreCase(nameIgnoreCase);
	}
}
