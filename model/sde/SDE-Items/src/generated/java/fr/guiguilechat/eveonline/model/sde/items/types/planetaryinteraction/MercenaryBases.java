
package fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction;

import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryInteraction;

public class MercenaryBases
    extends PlanetaryInteraction
{


    @Override
    public int getGroupId() {
        return  1081;
    }

    @Override
    public Class<?> getGroup() {
        return MercenaryBases.class;
    }

}
