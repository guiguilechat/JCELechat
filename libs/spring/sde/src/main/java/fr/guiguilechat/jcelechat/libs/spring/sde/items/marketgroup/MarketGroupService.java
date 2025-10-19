package fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class MarketGroupService
		extends SdeEntityService<MarketGroup, Integer, MarketGroupRepository> {

	public MarketGroupService() {
		super(MarketGroup::new);
	}

	// use

	/** list of market groups that have no parent (the roots) */
	public List<MarketGroup> roots() {
		return repo().findByParentNull();
	}

}
