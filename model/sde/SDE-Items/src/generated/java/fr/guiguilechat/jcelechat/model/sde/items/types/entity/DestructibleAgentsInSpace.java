package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DestructibleAgentsInSpace
    extends Entity
{

    @Override
    public int getGroupId() {
        return  715;
    }

    @Override
    public Class<?> getGroup() {
        return DestructibleAgentsInSpace.class;
    }
}
