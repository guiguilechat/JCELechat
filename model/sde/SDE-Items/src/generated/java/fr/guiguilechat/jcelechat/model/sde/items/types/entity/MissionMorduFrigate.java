package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionMorduFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  699;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMorduFrigate.class;
    }
}
