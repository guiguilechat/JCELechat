package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;

public class StructureObservatoryServiceModule
    extends StructureModule
{

    @Override
    public int getGroupId() {
        return  1323;
    }

    @Override
    public Class<?> getGroup() {
        return StructureObservatoryServiceModule.class;
    }
}
