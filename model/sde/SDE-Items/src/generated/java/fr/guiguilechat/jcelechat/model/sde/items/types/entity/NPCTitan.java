package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCTitan
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1878;
    }

    @Override
    public Class<?> getGroup() {
        return NPCTitan.class;
    }
}
