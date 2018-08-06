package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class PhysicalBenefit
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  191;
    }

    @Override
    public Class<?> getGroup() {
        return PhysicalBenefit.class;
    }
}
