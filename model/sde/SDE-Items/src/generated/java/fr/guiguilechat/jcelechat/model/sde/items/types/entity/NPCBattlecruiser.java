package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCBattlecruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1909;
    }

    @Override
    public Class<?> getGroup() {
        return NPCBattlecruiser.class;
    }
}
