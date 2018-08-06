package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class Convoy
    extends Entity
{

    @Override
    public int getGroupId() {
        return  297;
    }

    @Override
    public Class<?> getGroup() {
        return Convoy.class;
    }
}
