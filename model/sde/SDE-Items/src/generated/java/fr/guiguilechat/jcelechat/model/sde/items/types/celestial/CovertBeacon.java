package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class CovertBeacon
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  897;
    }

    @Override
    public Class<?> getGroup() {
        return CovertBeacon.class;
    }
}
