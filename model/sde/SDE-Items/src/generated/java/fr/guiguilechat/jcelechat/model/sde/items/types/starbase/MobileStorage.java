package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;

public class MobileStorage
    extends Starbase
{

    @Override
    public int getGroupId() {
        return  364;
    }

    @Override
    public Class<?> getGroup() {
        return MobileStorage.class;
    }
}
