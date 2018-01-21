
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class SpecialEditionAssets
    extends Item
{


    @Override
    public int getCategoryId() {
        return  63;
    }

    @Override
    public Class<?> getCategory() {
        return SpecialEditionAssets.class;
    }

}
