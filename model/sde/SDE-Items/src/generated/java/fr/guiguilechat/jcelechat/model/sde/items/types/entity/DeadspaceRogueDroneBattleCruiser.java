package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceRogueDroneBattleCruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  801;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceRogueDroneBattleCruiser.class;
    }
}
