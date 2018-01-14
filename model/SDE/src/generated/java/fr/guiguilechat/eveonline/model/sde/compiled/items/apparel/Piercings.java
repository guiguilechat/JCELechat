
package fr.guiguilechat.eveonline.model.sde.compiled.items.apparel;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Apparel;

public class Piercings
    extends Apparel
{


    @Override
    public int getGroupId() {
        return  1085;
    }

    @Override
    public Class<?> getGroup() {
        return Piercings.class;
    }

}
