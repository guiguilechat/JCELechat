package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class Billboard
    extends Entity
{

    @Override
    public int getGroupId() {
        return  323;
    }

    @Override
    public Class<?> getGroup() {
        return Billboard.class;
    }
}
