package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceOverseer
    extends Entity
{

    @Override
    public int getGroupId() {
        return  435;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceOverseer.class;
    }
}
