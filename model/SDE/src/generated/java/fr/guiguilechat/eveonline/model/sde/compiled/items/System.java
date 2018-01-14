
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class System
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  0;
    }

    @Override
    public Class<?> getCategory() {
        return System.class;
    }

}
