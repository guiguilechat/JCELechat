package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGallenteFederationFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  677;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGallenteFederationFrigate.class;
    }
}
