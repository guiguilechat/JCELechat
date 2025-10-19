package fr.guiguilechat.jcelechat.libs.spring.sde.universe.asteroidbelt;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class AsteroidBeltService extends
		SdeEntityService<AsteroidBelt, Integer, AsteroidBeltRepository> {


	public AsteroidBeltService() {
		super(AsteroidBelt::new);
	}

}
