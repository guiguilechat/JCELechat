package fr.guiguilechat.eveonline.model.sde.items.types.module;

import fr.guiguilechat.eveonline.model.sde.items.types.Module;

public class CheatModuleGroup
    extends Module
{

    @Override
    public int getGroupId() {
        return  225;
    }

    @Override
    public Class<?> getGroup() {
        return CheatModuleGroup.class;
    }
}
