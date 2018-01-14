
package fr.guiguilechat.eveonline.model.sde.compiled.items.worldspace;

import fr.guiguilechat.eveonline.model.sde.compiled.items.WorldSpace;

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
