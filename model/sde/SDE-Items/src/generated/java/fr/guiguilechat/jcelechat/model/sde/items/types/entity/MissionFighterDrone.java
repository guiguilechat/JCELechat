package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionFighterDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  861;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFighterDrone.class;
    }
}
