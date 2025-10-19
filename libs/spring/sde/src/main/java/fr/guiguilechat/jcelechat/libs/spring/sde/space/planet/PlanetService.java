package fr.guiguilechat.jcelechat.libs.spring.sde.space.planet;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class PlanetService extends
		SdeEntityService<Planet, Integer, PlanetRepository> {

	public PlanetService() {
		super(Planet::new);
	}

}
