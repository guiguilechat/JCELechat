package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Bonus
    extends Item
{

    @Override
    public int getCategoryId() {
        return  14;
    }

    @Override
    public Class<?> getCategory() {
        return Bonus.class;
    }
}
