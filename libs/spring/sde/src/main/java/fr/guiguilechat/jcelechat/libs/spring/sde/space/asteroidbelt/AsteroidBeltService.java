package fr.guiguilechat.jcelechat.libs.spring.sde.space.asteroidbelt;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class AsteroidBeltService extends
		SdeEntityService<AsteroidBelt, Integer, AsteroidBeltRepository> {


	public AsteroidBeltService() {
		super(AsteroidBelt::new);
	}

}
