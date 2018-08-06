package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;

public class StructureLaboratoryRigMLargeShip
    extends StructureModule
{

    @Override
    public int getGroupId() {
        return  1600;
    }

    @Override
    public Class<?> getGroup() {
        return StructureLaboratoryRigMLargeShip.class;
    }
}
