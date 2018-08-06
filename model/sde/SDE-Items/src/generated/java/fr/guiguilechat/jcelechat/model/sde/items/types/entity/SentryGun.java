package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class SentryGun
    extends Entity
{

    @Override
    public int getGroupId() {
        return  99;
    }

    @Override
    public Class<?> getGroup() {
        return SentryGun.class;
    }
}
