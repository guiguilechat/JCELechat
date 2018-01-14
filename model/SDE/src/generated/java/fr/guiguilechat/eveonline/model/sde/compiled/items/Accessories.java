
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Accessories
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  5;
    }

    @Override
    public Class<?> getCategory() {
        return Accessories.class;
    }

}
