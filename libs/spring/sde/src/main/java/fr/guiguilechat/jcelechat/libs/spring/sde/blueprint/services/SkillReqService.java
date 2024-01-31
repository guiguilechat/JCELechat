package fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.SkillReq;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.repositories.SkillReqRepository;

@Service
public class SkillReqService {

	@Autowired
	private SkillReqRepository repo;

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
			List<ACTIVITY_TYPE> ats) {
		return repo.findAllByActivityTypeTypeIdInAndActivityActivityIn(bpTypeIds, ats);
	}

	public List<SkillReq> forBPActivity(int bpTypeId,
			ACTIVITY_TYPE ats) {
		return forBPActivity(List.of(bpTypeId), List.of(ats));
	}

}
