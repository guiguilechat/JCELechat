
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class WorldSpace
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  26;
    }

    @Override
    public Class<?> getCategory() {
        return WorldSpace.class;
    }

}
