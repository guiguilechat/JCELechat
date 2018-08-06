package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class FWGallenteFederationFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1168;
    }

    @Override
    public Class<?> getGroup() {
        return FWGallenteFederationFrigate.class;
    }
}
