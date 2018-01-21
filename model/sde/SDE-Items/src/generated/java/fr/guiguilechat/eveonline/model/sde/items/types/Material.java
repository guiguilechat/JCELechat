
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class Material
    extends Item
{


    @Override
    public int getCategoryId() {
        return  4;
    }

    @Override
    public Class<?> getCategory() {
        return Material.class;
    }

}
