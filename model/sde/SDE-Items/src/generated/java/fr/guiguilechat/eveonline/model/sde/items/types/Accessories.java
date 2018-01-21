
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Accessories
    extends Item
{


    @Override
    public int getCategoryId() {
        return  5;
    }

    @Override
    public Class<?> getCategory() {
        return Accessories.class;
    }

}
