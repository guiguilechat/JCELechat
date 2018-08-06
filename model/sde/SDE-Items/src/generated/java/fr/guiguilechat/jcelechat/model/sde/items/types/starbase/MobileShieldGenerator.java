package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;

public class MobileShieldGenerator
    extends Starbase
{

    @Override
    public int getGroupId() {
        return  418;
    }

    @Override
    public Class<?> getGroup() {
        return MobileShieldGenerator.class;
    }
}
