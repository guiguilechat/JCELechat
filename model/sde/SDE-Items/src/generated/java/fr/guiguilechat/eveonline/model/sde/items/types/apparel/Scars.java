package fr.guiguilechat.eveonline.model.sde.items.types.apparel;

import fr.guiguilechat.eveonline.model.sde.items.types.Apparel;

public class Scars
    extends Apparel
{

    @Override
    public int getGroupId() {
        return  1086;
    }

    @Override
    public Class<?> getGroup() {
        return Scars.class;
    }
}