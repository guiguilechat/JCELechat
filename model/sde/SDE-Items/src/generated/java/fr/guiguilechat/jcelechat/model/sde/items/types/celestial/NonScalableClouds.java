package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class NonScalableClouds
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1980;
    }

    @Override
    public Class<?> getGroup() {
        return NonScalableClouds.class;
    }
}
