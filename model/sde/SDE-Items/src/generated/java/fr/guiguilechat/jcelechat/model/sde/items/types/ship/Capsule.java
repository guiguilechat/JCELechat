package fr.guiguilechat.jcelechat.model.sde.items.types.ship;

import fr.guiguilechat.jcelechat.model.sde.items.types.Ship;

public class Capsule
    extends Ship
{

    @Override
    public int getGroupId() {
        return  29;
    }

    @Override
    public Class<?> getGroup() {
        return Capsule.class;
    }
}
