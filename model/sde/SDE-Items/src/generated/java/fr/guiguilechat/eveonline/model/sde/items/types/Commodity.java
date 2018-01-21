
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Commodity
    extends Item
{


    @Override
    public int getCategoryId() {
        return  17;
    }

    @Override
    public Class<?> getCategory() {
        return Commodity.class;
    }

}
