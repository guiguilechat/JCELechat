package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Satellite
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1165;
    }

    @Override
    public Class<?> getGroup() {
        return Satellite.class;
    }
}
