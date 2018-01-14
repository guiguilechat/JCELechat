
package fr.guiguilechat.eveonline.model.sde.compiled.items.drone;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Drone;

public class FighterBomber
    extends Drone
{


    @Override
    public int getGroupId() {
        return  1023;
    }

    @Override
    public Class<?> getGroup() {
        return FighterBomber.class;
    }

}
