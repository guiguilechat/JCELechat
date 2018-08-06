package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class BattleSalvage
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  368656;
    }

    @Override
    public Class<?> getGroup() {
        return BattleSalvage.class;
    }
}
