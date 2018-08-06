package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionKhanidFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  687;
    }

    @Override
    public Class<?> getGroup() {
        return MissionKhanidFrigate.class;
    }
}
