
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class SuperKerrInducedNanocoatings
    extends EveItem
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
