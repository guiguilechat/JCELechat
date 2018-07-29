package fr.guiguilechat.jcelechat.model.sde.items.types.drone;

import fr.guiguilechat.jcelechat.model.sde.items.types.Drone;

public class FighterDrone
    extends Drone
{

    @Override
    public int getGroupId() {
        return  549;
    }

    @Override
    public Class<?> getGroup() {
        return FighterDrone.class;
    }
}
