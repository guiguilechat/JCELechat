package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class EntosisCommandNode
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1316;
    }

    @Override
    public Class<?> getGroup() {
        return EntosisCommandNode.class;
    }
}
