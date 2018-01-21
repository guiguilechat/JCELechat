
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Infantry
    extends Item
{


    @Override
    public int getCategoryId() {
        return  350001;
    }

    @Override
    public Class<?> getCategory() {
        return Infantry.class;
    }

}
