package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class TemporaryCloud
    extends Entity
{

    @Override
    public int getGroupId() {
        return  335;
    }

    @Override
    public Class<?> getGroup() {
        return TemporaryCloud.class;
    }
}
