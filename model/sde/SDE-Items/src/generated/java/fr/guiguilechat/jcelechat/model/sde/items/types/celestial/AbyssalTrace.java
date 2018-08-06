package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class AbyssalTrace
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1991;
    }

    @Override
    public Class<?> getGroup() {
        return AbyssalTrace.class;
    }
}
