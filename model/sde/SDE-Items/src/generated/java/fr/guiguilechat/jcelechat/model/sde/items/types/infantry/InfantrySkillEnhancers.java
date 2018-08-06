package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantrySkillEnhancers
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  354641;
    }

    @Override
    public Class<?> getGroup() {
        return InfantrySkillEnhancers.class;
    }
}
