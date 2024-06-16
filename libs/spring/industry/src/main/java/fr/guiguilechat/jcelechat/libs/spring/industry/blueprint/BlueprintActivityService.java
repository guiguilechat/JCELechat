package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SDEUpdateService.SdeUpdateListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BlueprintActivityService implements SdeUpdateListener {

	final private BlueprintActivityRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<BlueprintActivity> saveAll(Iterable<BlueprintActivity> entities) {
		return repo.saveAll(entities);
	}

	public BlueprintActivity save(BlueprintActivity entity) {
		return repo.save(entity);
	}

	public List<BlueprintActivity> forBPActivity(List<Integer> bpTypeIds,
			List<ACTIVITY_TYPE> ats) {
		return repo.findAllByTypeTypeIdInAndActivityIn(bpTypeIds, ats);
	}

	public List<BlueprintActivity> forType(Type type) {
		return repo.findAllByTypeTypeIdIn(List.of(type.getId()));
	}

	@Cacheable("SdeBlueprintActivity")
	public List<BlueprintActivity> forBPActivity(int bpTypeId,
			ACTIVITY_TYPE ats) {
		return forBPActivity(List.of(bpTypeId), List.of(ats));
	}

	public static final List<String> CACHE_LIST = List.of("SdeBlueprintActivity");

	@Override
	public List<String> listSDECaches() {
		return CACHE_LIST;
	}

}
