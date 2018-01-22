package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Abstrct
    extends Item
{

    @Override
    public int getCategoryId() {
        return  29;
    }

    @Override
    public Class<?> getCategory() {
        return Abstrct.class;
    }
}
