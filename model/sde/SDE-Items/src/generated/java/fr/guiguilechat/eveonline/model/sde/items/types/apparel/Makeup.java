package fr.guiguilechat.eveonline.model.sde.items.types.apparel;

import fr.guiguilechat.eveonline.model.sde.items.types.Apparel;

public class Makeup
    extends Apparel
{

    @Override
    public int getGroupId() {
        return  1093;
    }

    @Override
    public Class<?> getGroup() {
        return Makeup.class;
    }
}
