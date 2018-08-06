package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;

public class MobilePowerCore
    extends Starbase
{

    @Override
    public int getGroupId() {
        return  414;
    }

    @Override
    public Class<?> getGroup() {
        return MobilePowerCore.class;
    }
}
