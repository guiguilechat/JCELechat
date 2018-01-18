
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Placeables
    extends EveItem
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
