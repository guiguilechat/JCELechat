package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Reaction
    extends Item
{

    @Override
    public int getCategoryId() {
        return  24;
    }

    @Override
    public Class<?> getCategory() {
        return Reaction.class;
    }
}
