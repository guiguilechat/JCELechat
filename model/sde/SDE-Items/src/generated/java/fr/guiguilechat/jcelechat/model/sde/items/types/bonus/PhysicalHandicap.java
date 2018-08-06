package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class PhysicalHandicap
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  192;
    }

    @Override
    public Class<?> getGroup() {
        return PhysicalHandicap.class;
    }
}
