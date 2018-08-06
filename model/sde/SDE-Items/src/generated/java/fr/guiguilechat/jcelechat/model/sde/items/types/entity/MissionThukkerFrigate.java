package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionThukkerFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  826;
    }

    @Override
    public Class<?> getGroup() {
        return MissionThukkerFrigate.class;
    }
}
