package fr.guiguilechat.jcelechat.model.sde.items.types.drone;

import fr.guiguilechat.jcelechat.model.sde.items.types.Drone;

public class UnanchoringDrone
    extends Drone
{

    @Override
    public int getGroupId() {
        return  470;
    }

    @Override
    public Class<?> getGroup() {
        return UnanchoringDrone.class;
    }
}
