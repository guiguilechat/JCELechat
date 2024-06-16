package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SDEUpdateService.SdeUpdateListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService implements SdeUpdateListener {

	final private MaterialRepository repo;

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
		return repo.findAllByActivityTypeIdInAndActivityActivityIn(bpTypeIds, ats);
	}

	@Cacheable("SdeBlueprintMaterial")
	public List<Material> forBPActivity(int bpTypeId,
	    ACTIVITY_TYPE ats) {
		return forBPActivity(List.of(bpTypeId), List.of(ats));
	}

	public List<Material> findUsages(List<Integer> bpTypeIds,
			List<ACTIVITY_TYPE> ats) {
		return repo.findAllByTypeIdInAndActivityActivityIn(bpTypeIds, ats);
	}

	@Cacheable("SdeBlueprintUsage")
	public List<Material> findUsages(int typeId, ACTIVITY_TYPE ats) {
		return findUsages(List.of(typeId), List.of(ats));
	}

// @Cacheable("SdeBlueprintActivitytypeMaterials")
	public Set<Type> allActivityMaterialsInCategory(ACTIVITY_TYPE at, int category_id) {
		return repo.allActivityMaterialsInCategory(at, category_id);
	}

	public static final List<String> CACHE_LIST = List.of(
// "SdeBlueprintActivitytypeMaterials",
			"SdeBlueprintMaterial",
			"SdeBlueprintUsage");

	@Override
	public List<String> listSDECaches() {
		return CACHE_LIST;
	}

}
