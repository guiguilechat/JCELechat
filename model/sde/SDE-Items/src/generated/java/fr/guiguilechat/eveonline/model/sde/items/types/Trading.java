
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Trading
    extends Item
{


    @Override
    public int getCategoryId() {
        return  10;
    }

    @Override
    public Class<?> getCategory() {
        return Trading.class;
    }

}
