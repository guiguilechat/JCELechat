package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class IrregularFreighter
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1926;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularFreighter.class;
    }
}
