package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MinorThreat
    extends Entity
{

    @Override
    public int getGroupId() {
        return  286;
    }

    @Override
    public Class<?> getGroup() {
        return MinorThreat.class;
    }
}
