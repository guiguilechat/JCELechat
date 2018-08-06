package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceSerpentisFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  633;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSerpentisFrigate.class;
    }
}
