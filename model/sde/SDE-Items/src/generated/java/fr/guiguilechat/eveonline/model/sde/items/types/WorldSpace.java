package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class WorldSpace
    extends Item
{

    @Override
    public int getCategoryId() {
        return  26;
    }

    @Override
    public Class<?> getCategory() {
        return WorldSpace.class;
    }
}
