package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionFactionFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1007;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionFrigate.class;
    }
}
