package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Constellation
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  4;
    }

    @Override
    public Class<?> getGroup() {
        return Constellation.class;
    }
}
