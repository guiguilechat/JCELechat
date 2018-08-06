package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class StationUpgradePlatform
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  835;
    }

    @Override
    public Class<?> getGroup() {
        return StationUpgradePlatform.class;
    }
}
