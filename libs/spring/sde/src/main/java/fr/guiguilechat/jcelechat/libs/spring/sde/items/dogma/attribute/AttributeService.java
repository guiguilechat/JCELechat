package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class AttributeService
		extends SdeEntityService<
        Attribute,
        Integer,
				AttributeRepository> {

	public AttributeService() {
		super(Attribute::new);
	}

}
