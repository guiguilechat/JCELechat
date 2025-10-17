package fr.guiguilechat.jcelechat.libs.spring.universe.star;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class StarService extends
		SdeEntityService<Star, Integer, StarRepository> {

	public StarService() {
		super(Star::new);
	}

}
