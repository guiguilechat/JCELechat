
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;

public abstract class SuperKerrInducedNanocoatings
    extends Item
{


    @Override
    public int getCategoryId() {
        return  91;
    }

    @Override
    public Class<?> getCategory() {
        return SuperKerrInducedNanocoatings.class;
    }

}
