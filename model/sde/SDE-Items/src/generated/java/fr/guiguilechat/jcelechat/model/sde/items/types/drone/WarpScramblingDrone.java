package fr.guiguilechat.jcelechat.model.sde.items.types.drone;

import fr.guiguilechat.jcelechat.model.sde.items.types.Drone;

public class WarpScramblingDrone
    extends Drone
{

    @Override
    public int getGroupId() {
        return  545;
    }

    @Override
    public Class<?> getGroup() {
        return WarpScramblingDrone.class;
    }
}
