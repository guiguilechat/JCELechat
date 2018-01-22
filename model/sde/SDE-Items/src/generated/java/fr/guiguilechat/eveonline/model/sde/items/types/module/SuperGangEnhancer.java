package fr.guiguilechat.eveonline.model.sde.items.types.module;

import fr.guiguilechat.eveonline.model.sde.items.types.Module;

public class SuperGangEnhancer
    extends Module
{

    @Override
    public int getGroupId() {
        return  642;
    }

    @Override
    public Class<?> getGroup() {
        return SuperGangEnhancer.class;
    }
}
