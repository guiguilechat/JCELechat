
package fr.guiguilechat.eveonline.model.sde.compiled.items.material;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Material;

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
