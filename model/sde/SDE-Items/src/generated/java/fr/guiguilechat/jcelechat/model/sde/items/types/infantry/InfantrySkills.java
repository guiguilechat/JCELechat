package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantrySkills
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  351648;
    }

    @Override
    public Class<?> getGroup() {
        return InfantrySkills.class;
    }
}
