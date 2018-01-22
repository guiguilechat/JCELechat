package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;

public class AsteroidOLD
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  11;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidOLD.class;
    }
}
