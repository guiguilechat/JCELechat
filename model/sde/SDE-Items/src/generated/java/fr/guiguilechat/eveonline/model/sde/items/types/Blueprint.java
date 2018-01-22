package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Blueprint
    extends Item
{

    @Override
    public int getCategoryId() {
        return  9;
    }

    @Override
    public Class<?> getCategory() {
        return Blueprint.class;
    }
}
