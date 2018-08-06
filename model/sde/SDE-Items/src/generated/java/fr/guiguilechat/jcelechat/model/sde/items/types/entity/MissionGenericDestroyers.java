package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGenericDestroyers
    extends Entity
{

    @Override
    public int getGroupId() {
        return  829;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericDestroyers.class;
    }
}
