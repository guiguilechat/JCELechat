package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class HiddenZenithMinmatarFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1800;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithMinmatarFrigate.class;
    }
}
