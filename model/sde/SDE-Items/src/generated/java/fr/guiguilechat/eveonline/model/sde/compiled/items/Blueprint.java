
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Blueprint
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  9;
    }

    @Override
    public Class<?> getCategory() {
        return Blueprint.class;
    }

}
