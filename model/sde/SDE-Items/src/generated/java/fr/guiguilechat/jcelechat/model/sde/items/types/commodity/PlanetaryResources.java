package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

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
