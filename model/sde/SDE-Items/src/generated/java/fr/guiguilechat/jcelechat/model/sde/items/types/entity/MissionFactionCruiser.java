package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionFactionCruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1006;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionCruiser.class;
    }
}
