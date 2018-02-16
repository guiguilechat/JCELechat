package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class IrregularShuttle
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1566;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularShuttle.class;
    }
}
