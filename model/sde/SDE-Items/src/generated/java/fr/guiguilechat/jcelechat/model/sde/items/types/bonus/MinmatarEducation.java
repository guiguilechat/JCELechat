package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class MinmatarEducation
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  198;
    }

    @Override
    public Class<?> getGroup() {
        return MinmatarEducation.class;
    }
}
