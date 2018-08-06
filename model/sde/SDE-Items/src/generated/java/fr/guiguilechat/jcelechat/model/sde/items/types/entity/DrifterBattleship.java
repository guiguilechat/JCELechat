package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DrifterBattleship
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1310;
    }

    @Override
    public Class<?> getGroup() {
        return DrifterBattleship.class;
    }
}
