package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services;

import java.util.List;
import java.util.Objects;
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


	public Optional<Type> byId(int typeId) {
		return repo.findById(typeId);
	}

	public List<Type> byName(String nameIgnoreCase) {
		return repo.findByNameEqualsIgnoreCase(nameIgnoreCase);
	}

	public List<Type> byGroupId(int groupId) {
		return repo.findByGroupGroupId(groupId);
	}

	public List<Type> byGroupName(String groupName) {
		return repo.findByGroupNameEqualsIgnoreCase(groupName);
	}

	public List<Type> typesFilter(String typeFiltering, String typeFilter) {
		return switch (Objects.requireNonNullElse(typeFiltering, "name").toLowerCase()) {
			case "gn", "gname", "groupname" -> byGroupName(typeFilter);
			case "gi", "gid", "groupid" -> byGroupId(Integer.parseInt(typeFilter));
			case "id", "ti", "tid", "typeid" -> List.of(byId(Integer.parseInt(typeFilter)).orElse(null));
			case "name", "tn", "tname" -> byName(typeFilter);
			default -> byName(typeFilter);
		};
	}

	public Type typeFilter(String typeFiltering, String typeFilter) {
		return switch (Objects.requireNonNullElse(typeFiltering, "name").toLowerCase()) {
			case "id", "ti", "tid", "typeid" -> byId(Integer.parseInt(typeFilter)).orElse(null);
			case "name", "tn", "tname" -> byName(typeFilter).stream().findFirst().orElse(null);
			default -> byName(typeFilter).stream().findFirst().orElse(null);
		};
	}

	public Type prevType(Type type) {
		return repo.findTop1ByGroupAndNameLessThanOrderByNameDesc(type.getGroup(), type.getName());
	}

	public Type nextType(Type type) {
		return repo.findTop1ByGroupAndNameGreaterThanOrderByNameAsc(type.getGroup(), type.getName());
	}

}
