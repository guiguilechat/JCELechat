package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class FactionDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  288;
    }

    @Override
    public Class<?> getGroup() {
        return FactionDrone.class;
    }
}
