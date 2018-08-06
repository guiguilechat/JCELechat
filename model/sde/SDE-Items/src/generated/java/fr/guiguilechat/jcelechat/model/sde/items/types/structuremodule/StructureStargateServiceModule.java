package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;

public class StructureStargateServiceModule
    extends StructureModule
{

    @Override
    public int getGroupId() {
        return  1324;
    }

    @Override
    public Class<?> getGroup() {
        return StructureStargateServiceModule.class;
    }
}
