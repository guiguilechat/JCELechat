package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class MoonMiningBeacon
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1915;
    }

    @Override
    public Class<?> getGroup() {
        return MoonMiningBeacon.class;
    }
}
