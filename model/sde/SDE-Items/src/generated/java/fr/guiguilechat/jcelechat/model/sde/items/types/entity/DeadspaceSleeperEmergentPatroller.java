package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceSleeperEmergentPatroller
    extends Entity
{

    @Override
    public int getGroupId() {
        return  987;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperEmergentPatroller.class;
    }
}
