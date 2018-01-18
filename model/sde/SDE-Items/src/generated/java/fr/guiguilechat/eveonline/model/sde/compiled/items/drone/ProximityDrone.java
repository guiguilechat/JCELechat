
package fr.guiguilechat.eveonline.model.sde.compiled.items.drone;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Drone;

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
