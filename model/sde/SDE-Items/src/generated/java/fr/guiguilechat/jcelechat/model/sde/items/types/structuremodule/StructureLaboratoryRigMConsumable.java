package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;

public class StructureLaboratoryRigMConsumable
    extends StructureModule
{

    @Override
    public int getGroupId() {
        return  1602;
    }

    @Override
    public Class<?> getGroup() {
        return StructureLaboratoryRigMConsumable.class;
    }
}
