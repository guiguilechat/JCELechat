package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<Material> saveAll(Iterable<Material> entities) {
		return repo.saveAll(entities);
	}

	public Material save(Material entity) {
		return repo.save(entity);
	}

	public List<Material> forBPActivity(List<Integer> bpTypeIds,
			List<ACTIVITY_TYPE> ats) {
		return repo.findAllByActivityTypeTypeIdInAndActivityActivityIn(bpTypeIds, ats);
	}

	public List<Material> forBPActivity(int bpTypeId,
			ACTIVITY_TYPE ats) {
		return forBPActivity(List.of(bpTypeId), List.of(ats));
	}

}
