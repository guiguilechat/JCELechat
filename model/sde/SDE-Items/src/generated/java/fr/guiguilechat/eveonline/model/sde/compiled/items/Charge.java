
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Charge
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  8;
    }

    @Override
    public Class<?> getCategory() {
        return Charge.class;
    }

}
