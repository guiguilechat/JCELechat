package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class LCODrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  279;
    }

    @Override
    public Class<?> getGroup() {
        return LCODrone.class;
    }
}
