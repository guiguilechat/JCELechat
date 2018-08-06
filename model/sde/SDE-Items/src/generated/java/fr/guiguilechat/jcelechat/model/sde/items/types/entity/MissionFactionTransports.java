package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionFactionTransports
    extends Entity
{

    @Override
    public int getGroupId() {
        return  875;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionTransports.class;
    }
}
