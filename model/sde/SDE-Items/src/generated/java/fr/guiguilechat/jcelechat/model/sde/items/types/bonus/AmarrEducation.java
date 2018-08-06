package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;

public class AmarrEducation
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  195;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrEducation.class;
    }
}
