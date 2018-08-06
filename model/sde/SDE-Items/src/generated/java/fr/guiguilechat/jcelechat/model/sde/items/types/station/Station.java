package fr.guiguilechat.jcelechat.model.sde.items.types.station;

public class Station
    extends fr.guiguilechat.jcelechat.model.sde.items.types.Station
{

    @Override
    public int getGroupId() {
        return  15;
    }

    @Override
    public Class<?> getGroup() {
        return Station.class;
    }
}
