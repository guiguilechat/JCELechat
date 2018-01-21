
package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;

public class Flashpoint
    extends Celestial
{


    @Override
    public int getGroupId() {
        return  1071;
    }

    @Override
    public Class<?> getGroup() {
        return Flashpoint.class;
    }

}
