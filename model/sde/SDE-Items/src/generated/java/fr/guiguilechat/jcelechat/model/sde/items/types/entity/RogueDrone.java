package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class RogueDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  287;
    }

    @Override
    public Class<?> getGroup() {
        return RogueDrone.class;
    }
}
