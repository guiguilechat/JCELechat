package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class AgentsInSpace
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  517;
    }

    @Override
    public Class<?> getGroup() {
        return AgentsInSpace.class;
    }
}
