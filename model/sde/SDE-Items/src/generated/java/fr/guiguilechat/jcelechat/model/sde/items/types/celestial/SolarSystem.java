package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class SolarSystem
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  5;
    }

    @Override
    public Class<?> getGroup() {
        return SolarSystem.class;
    }
}
