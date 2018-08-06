package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DestructibleSentryGun
    extends Entity
{

    @Override
    public int getGroupId() {
        return  383;
    }

    @Override
    public Class<?> getGroup() {
        return DestructibleSentryGun.class;
    }
}
