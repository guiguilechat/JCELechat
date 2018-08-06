package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidGuristasCommanderFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  800;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasCommanderFrigate.class;
    }
}
