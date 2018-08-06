package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGenericCruisers
    extends Entity
{

    @Override
    public int getGroupId() {
        return  817;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericCruisers.class;
    }
}
