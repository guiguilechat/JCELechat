package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class AbyssalHazards
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1971;
    }

    @Override
    public Class<?> getGroup() {
        return AbyssalHazards.class;
    }
}
