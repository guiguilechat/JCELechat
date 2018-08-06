package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;

public class ShieldBoosterScript
    extends Charge
{

    @Override
    public int getGroupId() {
        return  1153;
    }

    @Override
    public Class<?> getGroup() {
        return ShieldBoosterScript.class;
    }
}
