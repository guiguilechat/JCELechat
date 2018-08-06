package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryEquipment
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  351844;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryEquipment.class;
    }
}
