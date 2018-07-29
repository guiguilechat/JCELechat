package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction;

import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryInteraction;

public class CapsuleerBases
    extends PlanetaryInteraction
{

    @Override
    public int getGroupId() {
        return  1082;
    }

    @Override
    public Class<?> getGroup() {
        return CapsuleerBases.class;
    }
}
