
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Charge
    extends Item
{


    @Override
    public int getCategoryId() {
        return  8;
    }

    @Override
    public Class<?> getCategory() {
        return Charge.class;
    }

}
