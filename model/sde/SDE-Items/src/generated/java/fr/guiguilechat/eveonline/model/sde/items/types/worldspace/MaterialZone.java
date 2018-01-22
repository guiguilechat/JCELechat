package fr.guiguilechat.eveonline.model.sde.items.types.worldspace;

import fr.guiguilechat.eveonline.model.sde.items.types.WorldSpace;

public class MaterialZone
    extends WorldSpace
{

    @Override
    public int getGroupId() {
        return  1067;
    }

    @Override
    public Class<?> getGroup() {
        return MaterialZone.class;
    }
}
