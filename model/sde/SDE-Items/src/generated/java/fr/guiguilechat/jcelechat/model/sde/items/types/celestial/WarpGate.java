package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class WarpGate
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  366;
    }

    @Override
    public Class<?> getGroup() {
        return WarpGate.class;
    }
}
