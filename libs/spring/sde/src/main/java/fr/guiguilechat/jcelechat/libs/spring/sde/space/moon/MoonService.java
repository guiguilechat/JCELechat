package fr.guiguilechat.jcelechat.libs.spring.sde.space.moon;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class MoonService extends SdeEntityService<Moon, Integer, MoonRepository> {

	public MoonService() {
		super(Moon::new);
	}

}
