package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class ConstructionPlatform
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  307;
    }

    @Override
    public Class<?> getGroup() {
        return ConstructionPlatform.class;
    }
}
