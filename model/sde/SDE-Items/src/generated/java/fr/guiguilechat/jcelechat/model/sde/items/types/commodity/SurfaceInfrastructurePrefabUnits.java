package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class SurfaceInfrastructurePrefabUnits
    extends Commodity
{

    @Override
    public int getGroupId() {
        return  1118;
    }

    @Override
    public Class<?> getGroup() {
        return SurfaceInfrastructurePrefabUnits.class;
    }
}
