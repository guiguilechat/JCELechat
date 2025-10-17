package fr.guiguilechat.jcelechat.libs.spring.universe.planet;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class PlanetService extends
		SdeEntityService<Planet, Integer, PlanetRepository> {

	public PlanetService() {
		super(Planet::new);
	}

}
