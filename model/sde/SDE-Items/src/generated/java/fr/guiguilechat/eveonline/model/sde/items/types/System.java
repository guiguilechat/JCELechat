package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class System
    extends Item
{

    @Override
    public int getCategoryId() {
        return  0;
    }

    @Override
    public Class<?> getCategory() {
        return System.class;
    }
}
