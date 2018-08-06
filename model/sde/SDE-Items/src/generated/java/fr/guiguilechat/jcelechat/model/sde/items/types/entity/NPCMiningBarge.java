package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCMiningBarge
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1765;
    }

    @Override
    public Class<?> getGroup() {
        return NPCMiningBarge.class;
    }
}
