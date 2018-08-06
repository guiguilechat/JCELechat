package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class PirateDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  185;
    }

    @Override
    public Class<?> getGroup() {
        return PirateDrone.class;
    }
}
