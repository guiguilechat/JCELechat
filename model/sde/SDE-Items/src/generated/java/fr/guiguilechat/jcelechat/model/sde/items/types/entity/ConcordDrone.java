package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class ConcordDrone
    extends Entity
{

    @Override
    public int getGroupId() {
        return  301;
    }

    @Override
    public Class<?> getGroup() {
        return ConcordDrone.class;
    }
}
