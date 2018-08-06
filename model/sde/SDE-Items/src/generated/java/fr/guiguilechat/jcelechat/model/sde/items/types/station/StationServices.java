package fr.guiguilechat.jcelechat.model.sde.items.types.station;

import fr.guiguilechat.jcelechat.model.sde.items.types.Station;

public class StationServices
    extends Station
{

    @Override
    public int getGroupId() {
        return  16;
    }

    @Override
    public Class<?> getGroup() {
        return StationServices.class;
    }
}
