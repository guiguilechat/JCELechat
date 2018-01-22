package fr.guiguilechat.eveonline.model.sde.items.types.module;

import fr.guiguilechat.eveonline.model.sde.items.types.Module;

public class CovertCynosuralFieldGenerator
    extends Module
{

    @Override
    public int getGroupId() {
        return  905;
    }

    @Override
    public Class<?> getGroup() {
        return CovertCynosuralFieldGenerator.class;
    }
}
