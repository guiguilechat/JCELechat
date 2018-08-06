package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class LargeCollidableShip
    extends Entity
{

    @Override
    public int getGroupId() {
        return  784;
    }

    @Override
    public Class<?> getGroup() {
        return LargeCollidableShip.class;
    }
}
