package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class IrregularEWDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1453;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularEWDrone.class;
    }
}
