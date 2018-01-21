
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;

public abstract class Apparel
    extends Item
{

    /**
     * Used to describe what sex a given item is meant for.
     * 
     *  1 = Male,
     *  2 = Unisex,
     *  3 = Female
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2)
    public int Gender;

    @Override
    public int getCategoryId() {
        return  30;
    }

    @Override
    public Class<?> getCategory() {
        return Apparel.class;
    }

}
