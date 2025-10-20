package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.unit;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class UnitService extends SdeEntityService<Unit, Integer, UnitRepository> {

	public UnitService() {
		super(Unit::new);
	}

}
