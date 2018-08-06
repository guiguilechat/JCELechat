package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class ShippingCrates
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  382;
    }

    @Override
    public Class<?> getGroup() {
        return ShippingCrates.class;
    }
}
