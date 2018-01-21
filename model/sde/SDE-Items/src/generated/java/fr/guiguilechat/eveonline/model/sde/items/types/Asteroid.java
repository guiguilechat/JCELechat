
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Asteroid
    extends Item
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
