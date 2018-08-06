package fr.guiguilechat.jcelechat.model.sde.items.types.owner;

import fr.guiguilechat.jcelechat.model.sde.items.types.Owner;

public class Faction
    extends Owner
{

    @Override
    public int getGroupId() {
        return  19;
    }

    @Override
    public Class<?> getGroup() {
        return Faction.class;
    }
}
