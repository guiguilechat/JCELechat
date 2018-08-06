package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;

public class StructureRepairArray
    extends Starbase
{

    @Override
    public int getGroupId() {
        return  840;
    }

    @Override
    public Class<?> getGroup() {
        return StructureRepairArray.class;
    }
}
