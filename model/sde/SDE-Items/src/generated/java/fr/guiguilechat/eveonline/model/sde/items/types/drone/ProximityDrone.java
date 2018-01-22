package fr.guiguilechat.eveonline.model.sde.items.types.drone;

import fr.guiguilechat.eveonline.model.sde.items.types.Drone;

public class ProximityDrone
    extends Drone
{

    @Override
    public int getGroupId() {
        return  97;
    }

    @Override
    public Class<?> getGroup() {
        return ProximityDrone.class;
    }
}
