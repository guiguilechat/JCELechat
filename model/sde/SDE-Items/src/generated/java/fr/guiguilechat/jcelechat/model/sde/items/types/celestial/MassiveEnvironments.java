package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class MassiveEnvironments
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1882;
    }

    @Override
    public Class<?> getGroup() {
        return MassiveEnvironments.class;
    }
}
