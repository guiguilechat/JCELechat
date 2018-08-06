package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class PoliceDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  182;
    }

    @Override
    public Class<?> getGroup() {
        return PoliceDrone.class;
    }
}
