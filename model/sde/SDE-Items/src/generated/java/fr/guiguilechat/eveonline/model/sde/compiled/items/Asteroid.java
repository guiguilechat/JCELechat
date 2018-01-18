
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Asteroid
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  25;
    }

    @Override
    public Class<?> getCategory() {
        return Asteroid.class;
    }

}
