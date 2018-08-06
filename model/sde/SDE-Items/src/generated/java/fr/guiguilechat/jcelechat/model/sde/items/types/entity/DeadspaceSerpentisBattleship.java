package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceSerpentisBattleship
    extends Entity
{

    @Override
    public int getGroupId() {
        return  630;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSerpentisBattleship.class;
    }
}
