package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SkillReqService implements SdeUpdateListener {

	final private SkillReqRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public List<SkillReq> saveAll(Iterable<SkillReq> entities) {
		return repo.saveAll(entities);
	}

	public SkillReq save(SkillReq entity) {
		return repo.save(entity);
	}

	public List<SkillReq> forBPActivity(List<Integer> bpTypeIds,
			List<ActivityType> ats) {
		return repo.findAllByActivityTypeIdInAndActivityActivityIn(bpTypeIds, ats);
	}

	@Cacheable("SdeBlueprintSkillReq")
	public List<SkillReq> forBPActivity(int bpTypeId,
			ActivityType ats) {
		return forBPActivity(List.of(bpTypeId), List.of(ats));
	}

	public static final List<String> CACHE_LIST = List.of(
			"SdeBlueprintSkillReq");

	@Override
	public List<String> listSDECaches() {
		return CACHE_LIST;
	}

}
