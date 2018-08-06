package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryVehicles
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  351210;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryVehicles.class;
    }
}
