package fr.guiguilechat.jcelechat.model.sde.items.types.owner;

import fr.guiguilechat.jcelechat.model.sde.items.types.Owner;

public class Corporation
    extends Owner
{

    @Override
    public int getGroupId() {
        return  2;
    }

    @Override
    public Class<?> getGroup() {
        return Corporation.class;
    }
}
