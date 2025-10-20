package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class EffectService
		extends SdeEntityService<Effect, Integer, EffectRepository> {

	public EffectService() {
		super(Effect::new);
	}

}
