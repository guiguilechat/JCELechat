package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class IrregularDreadnought
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1724;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularDreadnought.class;
    }
}
