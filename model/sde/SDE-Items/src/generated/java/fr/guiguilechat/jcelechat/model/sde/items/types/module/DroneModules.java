package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class DroneModules
    extends Module
{

    @Override
    public int getGroupId() {
        return  586;
    }

    @Override
    public Class<?> getGroup() {
        return DroneModules.class;
    }
}
