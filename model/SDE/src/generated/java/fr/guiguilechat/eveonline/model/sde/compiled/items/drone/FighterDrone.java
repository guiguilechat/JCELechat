
package fr.guiguilechat.eveonline.model.sde.compiled.items.drone;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Drone;

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
