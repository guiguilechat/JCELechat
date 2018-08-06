package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCDreadnought
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1880;
    }

    @Override
    public Class<?> getGroup() {
        return NPCDreadnought.class;
    }
}
