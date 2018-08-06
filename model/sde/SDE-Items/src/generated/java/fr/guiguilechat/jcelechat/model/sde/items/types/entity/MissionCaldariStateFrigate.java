package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionCaldariStateFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  671;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCaldariStateFrigate.class;
    }
}
