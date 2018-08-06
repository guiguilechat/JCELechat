package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceRogueDroneFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  805;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceRogueDroneFrigate.class;
    }
}
