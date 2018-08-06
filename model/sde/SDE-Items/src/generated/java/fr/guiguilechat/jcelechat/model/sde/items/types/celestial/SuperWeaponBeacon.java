package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class SuperWeaponBeacon
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1704;
    }

    @Override
    public Class<?> getGroup() {
        return SuperWeaponBeacon.class;
    }
}
