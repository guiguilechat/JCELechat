package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class Modifications
    extends Charge
{

    @Override
    public int getGroupId() {
        return  498;
    }

    @Override
    public Class<?> getGroup() {
        return Modifications.class;
    }
}
