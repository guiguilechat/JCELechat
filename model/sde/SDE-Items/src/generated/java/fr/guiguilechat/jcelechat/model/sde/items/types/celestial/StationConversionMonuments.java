package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class StationConversionMonuments
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1998;
    }

    @Override
    public Class<?> getGroup() {
        return StationConversionMonuments.class;
    }
}
