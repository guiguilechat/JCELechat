package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class IndustrialSupportFacility
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1978;
    }

    @Override
    public Class<?> getGroup() {
        return IndustrialSupportFacility.class;
    }
}
