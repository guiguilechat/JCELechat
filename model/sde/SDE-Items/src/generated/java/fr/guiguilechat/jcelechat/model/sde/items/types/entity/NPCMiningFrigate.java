package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCMiningFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1764;
    }

    @Override
    public Class<?> getGroup() {
        return NPCMiningFrigate.class;
    }
}
