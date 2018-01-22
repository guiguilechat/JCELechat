package fr.guiguilechat.eveonline.model.sde.items.types.drone;

import fr.guiguilechat.eveonline.model.sde.items.types.Drone;

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
