package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class ConvoyDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  298;
    }

    @Override
    public Class<?> getGroup() {
        return ConvoyDrone.class;
    }
}
