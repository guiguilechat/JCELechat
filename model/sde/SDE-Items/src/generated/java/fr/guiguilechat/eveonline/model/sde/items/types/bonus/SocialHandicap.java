package fr.guiguilechat.eveonline.model.sde.items.types.bonus;

import fr.guiguilechat.eveonline.model.sde.items.types.Bonus;

public class SocialHandicap
    extends Bonus
{

    @Override
    public int getGroupId() {
        return  194;
    }

    @Override
    public Class<?> getGroup() {
        return SocialHandicap.class;
    }
}
