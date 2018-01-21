
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Placeables
    extends Item
{


    @Override
    public int getCategoryId() {
        return  49;
    }

    @Override
    public Class<?> getCategory() {
        return Placeables.class;
    }

}
