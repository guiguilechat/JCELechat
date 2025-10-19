package fr.guiguilechat.jcelechat.libs.spring.sde.items.metagroup;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class MetaGroupService extends SdeEntityService<MetaGroup, Integer, MetaGroupRepository> {

	public MetaGroupService() {
		super(MetaGroup::new);
	}

}
