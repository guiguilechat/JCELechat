package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionFactionIndustrials
    extends Entity
{

    @Override
    public int getGroupId() {
        return  927;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionIndustrials.class;
    }
}
