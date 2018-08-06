package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceRogueDroneBattleship
    extends Entity
{

    @Override
    public int getGroupId() {
        return  802;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceRogueDroneBattleship.class;
    }
}
