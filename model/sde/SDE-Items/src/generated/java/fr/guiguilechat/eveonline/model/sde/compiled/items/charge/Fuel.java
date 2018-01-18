
package fr.guiguilechat.eveonline.model.sde.compiled.items.charge;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Charge;

public class Fuel
    extends Charge
{


    @Override
    public int getGroupId() {
        return  497;
    }

    @Override
    public Class<?> getGroup() {
        return Fuel.class;
    }

}
