package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class CareerBonus
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  199;
    }

    @Override
    public Class<?> getGroup() {
        return CareerBonus.class;
    }
}
