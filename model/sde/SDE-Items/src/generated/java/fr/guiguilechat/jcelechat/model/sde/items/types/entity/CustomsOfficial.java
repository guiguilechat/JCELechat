package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class CustomsOfficial
    extends Entity
{

    @Override
    public int getGroupId() {
        return  446;
    }

    @Override
    public Class<?> getGroup() {
        return CustomsOfficial.class;
    }
}
