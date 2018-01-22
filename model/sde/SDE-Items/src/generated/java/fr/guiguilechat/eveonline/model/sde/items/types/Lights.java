package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Lights
    extends Item
{

    @Override
    public int getCategoryId() {
        return  54;
    }

    @Override
    public Class<?> getCategory() {
        return Lights.class;
    }
}
