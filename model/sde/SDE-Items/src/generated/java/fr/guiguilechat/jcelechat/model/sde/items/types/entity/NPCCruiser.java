package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCCruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1813;
    }

    @Override
    public Class<?> getGroup() {
        return NPCCruiser.class;
    }
}
