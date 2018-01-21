
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Entity
    extends Item
{


    @Override
    public int getCategoryId() {
        return  11;
    }

    @Override
    public Class<?> getCategory() {
        return Entity.class;
    }

}
