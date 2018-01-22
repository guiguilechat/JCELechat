package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Cells
    extends Item
{

    @Override
    public int getCategoryId() {
        return  59;
    }

    @Override
    public Class<?> getCategory() {
        return Cells.class;
    }
}
