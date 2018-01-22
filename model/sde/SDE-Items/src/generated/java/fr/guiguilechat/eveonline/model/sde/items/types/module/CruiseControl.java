package fr.guiguilechat.eveonline.model.sde.items.types.module;

import fr.guiguilechat.eveonline.model.sde.items.types.Module;

public class CruiseControl
    extends Module
{

    @Override
    public int getGroupId() {
        return  378;
    }

    @Override
    public Class<?> getGroup() {
        return CruiseControl.class;
    }
}
