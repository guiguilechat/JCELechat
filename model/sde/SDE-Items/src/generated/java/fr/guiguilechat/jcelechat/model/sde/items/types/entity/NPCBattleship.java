package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCBattleship
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1814;
    }

    @Override
    public Class<?> getGroup() {
        return NPCBattleship.class;
    }
}
