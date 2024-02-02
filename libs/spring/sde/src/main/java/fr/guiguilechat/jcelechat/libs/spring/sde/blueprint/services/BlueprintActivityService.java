package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories.BlueprintActivityRepository;

@Service
public class BlueprintActivityService {

	@Autowired
	private BlueprintActivityRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<BlueprintActivity> saveAll(Iterable<BlueprintActivity> entities) {
		return repo.saveAll(entities);
	}

	public BlueprintActivity save(BlueprintActivity entity) {
		return repo.save(entity);
	}

	public List<BlueprintActivity> producing(List<Integer> typeIds) {
		return typeIds == null || typeIds.isEmpty() ? Collections.emptyList() : repo.producing(typeIds);
	}

	public List<BlueprintActivity> forBPActivity(List<Integer> bpTypeIds,
			List<ACTIVITY_TYPE> ats) {
		return repo.findAllByTypeTypeIdInAndActivityIn(bpTypeIds, ats);
	}

	public static final List<String> CACHE_LIST = List.of("SdeBlueprintActivity");

	@Cacheable("SdeBlueprintActivity")
	public List<BlueprintActivity> forBPActivity(int bpTypeId,
			ACTIVITY_TYPE ats) {
		return forBPActivity(List.of(bpTypeId), List.of(ats));
	}

}
