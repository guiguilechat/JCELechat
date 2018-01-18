
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;

public class PlanetaryResources
    extends Commodity
{


    @Override
    public int getGroupId() {
        return  1031;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetaryResources.class;
    }

}
