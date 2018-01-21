
package fr.guiguilechat.eveonline.model.sde.items.types.drone;

import fr.guiguilechat.eveonline.model.sde.items.types.Drone;

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
