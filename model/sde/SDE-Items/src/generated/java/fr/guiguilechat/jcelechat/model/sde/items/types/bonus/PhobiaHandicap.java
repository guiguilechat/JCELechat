package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class PhobiaHandicap
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  193;
    }

    @Override
    public Class<?> getGroup() {
        return PhobiaHandicap.class;
    }
}
