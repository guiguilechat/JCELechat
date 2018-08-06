package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCMiningExhumer
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1766;
    }

    @Override
    public Class<?> getGroup() {
        return NPCMiningExhumer.class;
    }
}
