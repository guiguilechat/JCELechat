
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;

public abstract class Apparel
    extends EveItem
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
    @DefaultValue(2.0D)
    public double Gender;

    @Override
    public int getCategoryId() {
        return  30;
    }

    @Override
    public Class<?> getCategory() {
        return Apparel.class;
    }

}
