package fr.guiguilechat.jcelechat.model.sde.items.types.drone;

import fr.guiguilechat.jcelechat.model.sde.items.types.Drone;

public class RepairDrone
    extends Drone
{

    @Override
    public int getGroupId() {
        return  299;
    }

    @Override
    public Class<?> getGroup() {
        return RepairDrone.class;
    }
}
