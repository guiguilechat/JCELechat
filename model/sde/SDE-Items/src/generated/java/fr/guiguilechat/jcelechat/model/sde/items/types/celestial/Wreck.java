package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Wreck
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  186;
    }

    @Override
    public Class<?> getGroup() {
        return Wreck.class;
    }
}
