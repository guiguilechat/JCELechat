package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class SecondarySun
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  995;
    }

    @Override
    public Class<?> getGroup() {
        return SecondarySun.class;
    }
}
