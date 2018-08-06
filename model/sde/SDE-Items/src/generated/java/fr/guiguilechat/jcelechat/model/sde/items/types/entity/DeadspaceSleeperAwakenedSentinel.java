package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceSleeperAwakenedSentinel
    extends Entity
{

    @Override
    public int getGroupId() {
        return  960;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperAwakenedSentinel.class;
    }
}
