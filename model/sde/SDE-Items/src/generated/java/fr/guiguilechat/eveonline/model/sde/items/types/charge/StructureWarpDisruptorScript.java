
package fr.guiguilechat.eveonline.model.sde.items.types.charge;

import fr.guiguilechat.eveonline.model.sde.items.types.Charge;

public class StructureWarpDisruptorScript
    extends Charge
{


    @Override
    public int getGroupId() {
        return  1551;
    }

    @Override
    public Class<?> getGroup() {
        return StructureWarpDisruptorScript.class;
    }

}
