
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Trading
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  10;
    }

    @Override
    public Class<?> getCategory() {
        return Trading.class;
    }

}
