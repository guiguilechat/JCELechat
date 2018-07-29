package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

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
