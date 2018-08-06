package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1803;
    }

    @Override
    public Class<?> getGroup() {
        return NPCFrigate.class;
    }
}
