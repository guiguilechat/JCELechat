package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class SurfaceInfrastructure
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  364204;
    }

    @Override
    public Class<?> getGroup() {
        return SurfaceInfrastructure.class;
    }
}
