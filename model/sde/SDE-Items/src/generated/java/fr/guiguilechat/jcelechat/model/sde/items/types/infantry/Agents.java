package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class Agents
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  367580;
    }

    @Override
    public Class<?> getGroup() {
        return Agents.class;
    }
}
