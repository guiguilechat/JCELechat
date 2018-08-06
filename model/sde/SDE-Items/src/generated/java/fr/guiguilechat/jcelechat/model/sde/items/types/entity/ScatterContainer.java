package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class ScatterContainer
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1207;
    }

    @Override
    public Class<?> getGroup() {
        return ScatterContainer.class;
    }
}
