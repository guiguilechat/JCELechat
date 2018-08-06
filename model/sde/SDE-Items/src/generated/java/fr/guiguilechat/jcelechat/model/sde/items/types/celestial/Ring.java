package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Ring
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  13;
    }

    @Override
    public Class<?> getGroup() {
        return Ring.class;
    }
}
