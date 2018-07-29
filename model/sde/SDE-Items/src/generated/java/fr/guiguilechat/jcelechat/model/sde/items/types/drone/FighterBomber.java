package fr.guiguilechat.jcelechat.model.sde.items.types.drone;

import fr.guiguilechat.jcelechat.model.sde.items.types.Drone;

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
