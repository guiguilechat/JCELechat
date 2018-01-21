
package fr.guiguilechat.eveonline.model.sde.items.types.charge;

import fr.guiguilechat.eveonline.model.sde.items.types.Charge;

public class StructureStasisWebifierScript
    extends Charge
{


    @Override
    public int getGroupId() {
        return  1550;
    }

    @Override
    public Class<?> getGroup() {
        return StructureStasisWebifierScript.class;
    }

}
