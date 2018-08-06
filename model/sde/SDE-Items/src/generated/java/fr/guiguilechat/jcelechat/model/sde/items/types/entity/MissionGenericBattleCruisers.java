package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGenericBattleCruisers
    extends Entity
{

    @Override
    public int getGroupId() {
        return  828;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericBattleCruisers.class;
    }
}
