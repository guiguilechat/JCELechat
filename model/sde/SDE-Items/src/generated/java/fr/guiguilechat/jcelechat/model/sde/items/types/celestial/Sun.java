package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Sun
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  6;
    }

    @Override
    public Class<?> getGroup() {
        return Sun.class;
    }
}
