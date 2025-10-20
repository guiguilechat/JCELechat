package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.category;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class AttributeCategoryService
		extends SdeEntityService<AttributeCategory, Integer, AttributeCategoryRepository> {

	public AttributeCategoryService() {
		super(AttributeCategory::new);
	}

}
