package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Beacon
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  310;
    }

    @Override
    public Class<?> getGroup() {
        return Beacon.class;
    }
}
