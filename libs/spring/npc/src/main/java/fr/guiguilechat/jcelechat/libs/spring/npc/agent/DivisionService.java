package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class DivisionService extends SdeEntityService<Division, Integer, DivisionRepository> {


	@Override
	public Division create(Integer entityId) {
		var ret = new Division();
		ret.setId(entityId);
		return ret;
	}

}
