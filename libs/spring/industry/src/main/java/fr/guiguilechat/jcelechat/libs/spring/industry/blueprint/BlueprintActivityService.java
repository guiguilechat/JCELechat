package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BlueprintActivityService implements SdeListener {

	final private BlueprintActivityRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<BlueprintActivity> saveAll(Iterable<BlueprintActivity> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public BlueprintActivity save(BlueprintActivity entity) {
		return repo.saveAndFlush(entity);
	}

	public List<BlueprintActivity> forBPActivity(List<Integer> bpTypeIds,
			List<ActivityType> ats) {
		return repo.findAllByTypeIdInAndActivityIn(bpTypeIds, ats);
	}

	public List<BlueprintActivity> forType(Type type) {
		return repo.findAllByTypeIdIn(List.of(type.getId()));
	}

	@Cacheable("SdeBlueprintActivity")
	public List<BlueprintActivity> forBPActivity(int bpTypeId,
			ActivityType ats) {
		return forBPActivity(List.of(bpTypeId), List.of(ats));
	}

	public static final List<String> CACHE_LIST = List.of("SdeBlueprintActivity");

	@Override
	public List<String> listSDECaches() {
		return CACHE_LIST;
	}

}
