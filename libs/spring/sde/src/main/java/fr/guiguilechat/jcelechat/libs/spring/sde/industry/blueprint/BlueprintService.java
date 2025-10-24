package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class BlueprintService extends SdeEntityService<Blueprint, Integer, BlueprintRepository> {

	public BlueprintService() {
		super(Blueprint::new);
	}

}
