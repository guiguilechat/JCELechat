package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGenericFrigates
    extends Entity
{

    @Override
    public int getGroupId() {
        return  818;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericFrigates.class;
    }
}
