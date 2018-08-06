package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DrifterReinforcements
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1956;
    }

    @Override
    public Class<?> getGroup() {
        return DrifterReinforcements.class;
    }
}
