package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class StructureModule
    extends Item
{

    @Override
    public int getCategoryId() {
        return  66;
    }

    @Override
    public Class<?> getCategory() {
        return StructureModule.class;
    }
}
