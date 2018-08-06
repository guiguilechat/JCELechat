package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Stargate
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  10;
    }

    @Override
    public Class<?> getGroup() {
        return Stargate.class;
    }
}
