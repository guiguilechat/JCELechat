package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  337;
    }

    @Override
    public Class<?> getGroup() {
        return MissionDrone.class;
    }
}
