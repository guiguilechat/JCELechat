package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IrregularDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1452;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularDrone.class;
    }
}
