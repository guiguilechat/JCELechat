package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryWeapons
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  350858;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryWeapons.class;
    }
}
