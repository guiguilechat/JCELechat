
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;

public class AsteroidOLD
    extends Celestial
{


    @Override
    public int getGroupId() {
        return  11;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidOLD.class;
    }

}
