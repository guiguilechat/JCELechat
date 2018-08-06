package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class StructureResistanceSwitcherScript
    extends Charge
{

    @Override
    public int getGroupId() {
        return  1559;
    }

    @Override
    public Class<?> getGroup() {
        return StructureResistanceSwitcherScript.class;
    }
}
