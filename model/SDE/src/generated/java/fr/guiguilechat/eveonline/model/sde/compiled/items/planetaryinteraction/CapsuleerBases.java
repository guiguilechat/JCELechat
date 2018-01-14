
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetaryinteraction;

import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryInteraction;

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
