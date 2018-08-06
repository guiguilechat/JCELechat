package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import fr.guiguilechat.jcelechat.model.sde.items.types.Material;

public class WormholeMinerals
    extends Material
{

    @Override
    public int getGroupId() {
        return  967;
    }

    @Override
    public Class<?> getGroup() {
        return WormholeMinerals.class;
    }
}
