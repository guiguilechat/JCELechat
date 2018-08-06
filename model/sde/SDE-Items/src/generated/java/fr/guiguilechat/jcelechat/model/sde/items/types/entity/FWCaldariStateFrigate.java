package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class FWCaldariStateFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1167;
    }

    @Override
    public Class<?> getGroup() {
        return FWCaldariStateFrigate.class;
    }
}
