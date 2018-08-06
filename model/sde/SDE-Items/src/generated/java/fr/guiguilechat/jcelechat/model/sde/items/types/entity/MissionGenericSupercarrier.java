package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGenericSupercarrier
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1465;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericSupercarrier.class;
    }
}
