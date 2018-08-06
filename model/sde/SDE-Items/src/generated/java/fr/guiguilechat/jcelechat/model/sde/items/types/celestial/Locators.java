package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Locators
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1973;
    }

    @Override
    public Class<?> getGroup() {
        return Locators.class;
    }
}
