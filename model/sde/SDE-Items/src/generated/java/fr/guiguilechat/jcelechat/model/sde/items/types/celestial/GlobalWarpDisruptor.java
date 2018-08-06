package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class GlobalWarpDisruptor
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  368;
    }

    @Override
    public Class<?> getGroup() {
        return GlobalWarpDisruptor.class;
    }
}
