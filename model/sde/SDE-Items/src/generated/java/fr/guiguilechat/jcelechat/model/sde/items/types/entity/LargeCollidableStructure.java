package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class LargeCollidableStructure
    extends Entity
{

    @Override
    public int getGroupId() {
        return  319;
    }

    @Override
    public Class<?> getGroup() {
        return LargeCollidableStructure.class;
    }
}
