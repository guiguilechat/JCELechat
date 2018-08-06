package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class PlanetSatellites
    extends Charge
{

    @Override
    public int getGroupId() {
        return  892;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetSatellites.class;
    }
}
