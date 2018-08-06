package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;

public class StructureAdministrationServiceModule
    extends StructureModule
{

    @Override
    public int getGroupId() {
        return  1325;
    }

    @Override
    public Class<?> getGroup() {
        return StructureAdministrationServiceModule.class;
    }
}
