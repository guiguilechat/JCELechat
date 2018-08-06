package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Wormhole
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  988;
    }

    @Override
    public Class<?> getGroup() {
        return Wormhole.class;
    }
}
