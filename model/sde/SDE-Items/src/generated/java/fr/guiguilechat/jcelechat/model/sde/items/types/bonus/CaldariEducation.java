package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class CaldariEducation
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  196;
    }

    @Override
    public Class<?> getGroup() {
        return CaldariEducation.class;
    }
}
