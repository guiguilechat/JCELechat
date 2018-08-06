package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class AsteroidBelt
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  9;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBelt.class;
    }
}
