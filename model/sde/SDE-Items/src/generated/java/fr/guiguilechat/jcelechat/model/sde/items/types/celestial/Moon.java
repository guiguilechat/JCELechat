package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Moon
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  8;
    }

    @Override
    public Class<?> getGroup() {
        return Moon.class;
    }
}
