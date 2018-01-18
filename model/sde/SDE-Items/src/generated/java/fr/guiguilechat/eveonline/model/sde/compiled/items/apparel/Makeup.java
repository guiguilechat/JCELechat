
package fr.guiguilechat.eveonline.model.sde.compiled.items.apparel;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Apparel;

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
