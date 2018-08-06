package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;

public class StructureQAModules
    extends StructureModule
{

    @Override
    public int getGroupId() {
        return  1962;
    }

    @Override
    public Class<?> getGroup() {
        return StructureQAModules.class;
    }
}
