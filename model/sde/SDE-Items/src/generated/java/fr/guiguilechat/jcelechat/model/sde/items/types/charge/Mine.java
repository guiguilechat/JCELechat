package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class Mine
    extends Charge
{

    @Override
    public int getGroupId() {
        return  92;
    }

    @Override
    public Class<?> getGroup() {
        return Mine.class;
    }
}
