package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class GallenteEducation
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  197;
    }

    @Override
    public Class<?> getGroup() {
        return GallenteEducation.class;
    }
}
