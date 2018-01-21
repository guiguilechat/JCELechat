
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Celestial
    extends Item
{


    @Override
    public int getCategoryId() {
        return  2;
    }

    @Override
    public Class<?> getCategory() {
        return Celestial.class;
    }

}
