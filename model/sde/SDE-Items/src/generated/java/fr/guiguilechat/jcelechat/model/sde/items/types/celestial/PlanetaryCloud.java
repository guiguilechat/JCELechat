package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class PlanetaryCloud
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  312;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetaryCloud.class;
    }
}
