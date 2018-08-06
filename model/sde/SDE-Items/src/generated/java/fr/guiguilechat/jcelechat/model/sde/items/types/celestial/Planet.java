package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Planet
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  7;
    }

    @Override
    public Class<?> getGroup() {
        return Planet.class;
    }
}
