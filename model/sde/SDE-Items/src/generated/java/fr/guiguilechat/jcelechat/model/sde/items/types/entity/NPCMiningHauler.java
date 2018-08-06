package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCMiningHauler
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1767;
    }

    @Override
    public Class<?> getGroup() {
        return NPCMiningHauler.class;
    }
}
