package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IrregularFighter
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1454;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularFighter.class;
    }
}
