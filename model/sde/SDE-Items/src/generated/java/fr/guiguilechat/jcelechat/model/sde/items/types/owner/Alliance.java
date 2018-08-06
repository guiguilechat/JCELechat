package fr.guiguilechat.jcelechat.model.sde.items.types.owner;

import fr.guiguilechat.jcelechat.model.sde.items.types.Owner;

public class Alliance
    extends Owner
{

    @Override
    public int getGroupId() {
        return  32;
    }

    @Override
    public Class<?> getGroup() {
        return Alliance.class;
    }
}
