package fr.guiguilechat.eveonline.model.sde.items.types.starbase;

import fr.guiguilechat.eveonline.model.sde.items.types.Starbase;

public class LogisticsArray
    extends Starbase
{

    @Override
    public int getGroupId() {
        return  710;
    }

    @Override
    public Class<?> getGroup() {
        return LogisticsArray.class;
    }
}
