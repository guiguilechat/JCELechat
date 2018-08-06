package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class Warbarge
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  368666;
    }

    @Override
    public Class<?> getGroup() {
        return Warbarge.class;
    }
}
