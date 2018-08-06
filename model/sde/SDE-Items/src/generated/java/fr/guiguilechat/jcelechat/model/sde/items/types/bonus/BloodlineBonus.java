package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class BloodlineBonus
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  190;
    }

    @Override
    public Class<?> getGroup() {
        return BloodlineBonus.class;
    }
}
