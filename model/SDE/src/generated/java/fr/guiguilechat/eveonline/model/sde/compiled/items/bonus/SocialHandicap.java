
package fr.guiguilechat.eveonline.model.sde.compiled.items.bonus;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Bonus;

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
