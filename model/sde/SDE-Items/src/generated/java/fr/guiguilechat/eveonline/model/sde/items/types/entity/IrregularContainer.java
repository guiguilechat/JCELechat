package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class IrregularContainer
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1928;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularContainer.class;
    }
}
