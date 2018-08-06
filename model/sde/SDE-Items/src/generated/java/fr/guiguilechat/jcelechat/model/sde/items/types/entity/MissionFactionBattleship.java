package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionFactionBattleship
    extends Entity
{

    @Override
    public int getGroupId() {
        return  924;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionBattleship.class;
    }
}
