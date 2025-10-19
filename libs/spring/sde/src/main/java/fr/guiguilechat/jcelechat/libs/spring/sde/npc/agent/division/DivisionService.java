package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.division;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class DivisionService extends SdeEntityService<Division, Integer, DivisionRepository> {

	public DivisionService() {
		super(Division::new);
	}

}
