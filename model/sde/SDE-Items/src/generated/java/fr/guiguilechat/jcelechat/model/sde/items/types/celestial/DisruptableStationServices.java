package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class DisruptableStationServices
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  874;
    }

    @Override
    public Class<?> getGroup() {
        return DisruptableStationServices.class;
    }
}
