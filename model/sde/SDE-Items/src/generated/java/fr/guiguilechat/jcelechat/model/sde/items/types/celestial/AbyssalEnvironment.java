package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class AbyssalEnvironment
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1983;
    }

    @Override
    public Class<?> getGroup() {
        return AbyssalEnvironment.class;
    }
}
