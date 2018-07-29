package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IrregularCorvette
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1567;
    }

    @Override
    public Class<?> getGroup() {
        return IrregularCorvette.class;
    }
}
