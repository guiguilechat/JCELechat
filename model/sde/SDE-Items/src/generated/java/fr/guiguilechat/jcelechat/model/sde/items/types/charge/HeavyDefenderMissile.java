package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class HeavyDefenderMissile
    extends Charge
{

    @Override
    public int getGroupId() {
        return  1158;
    }

    @Override
    public Class<?> getGroup() {
        return HeavyDefenderMissile.class;
    }
}
