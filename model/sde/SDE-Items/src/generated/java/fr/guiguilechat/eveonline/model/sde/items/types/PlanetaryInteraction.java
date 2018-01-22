package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class PlanetaryInteraction
    extends Item
{

    @Override
    public int getCategoryId() {
        return  41;
    }

    @Override
    public Class<?> getCategory() {
        return PlanetaryInteraction.class;
    }
}
