package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Cloud
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  227;
    }

    @Override
    public Class<?> getGroup() {
        return Cloud.class;
    }
}
