
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Lights
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  54;
    }

    @Override
    public Class<?> getCategory() {
        return Lights.class;
    }

}
