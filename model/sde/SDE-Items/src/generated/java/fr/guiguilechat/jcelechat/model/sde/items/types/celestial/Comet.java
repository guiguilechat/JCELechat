package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Comet
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  305;
    }

    @Override
    public Class<?> getGroup() {
        return Comet.class;
    }
}
