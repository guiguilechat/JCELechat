
package fr.guiguilechat.eveonline.model.sde.items.types.material;

import fr.guiguilechat.eveonline.model.sde.items.types.Material;

public class Drug
    extends Material
{


    @Override
    public int getGroupId() {
        return  20;
    }

    @Override
    public Class<?> getGroup() {
        return Drug.class;
    }

}
