package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class SmartbombSupercharger
    extends Module
{

    @Override
    public int getGroupId() {
        return  406;
    }

    @Override
    public Class<?> getGroup() {
        return SmartbombSupercharger.class;
    }
}
