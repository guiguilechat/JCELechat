package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryDropsuits
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  351064;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryDropsuits.class;
    }
}
