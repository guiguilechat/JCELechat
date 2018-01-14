
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Bonus
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  14;
    }

    @Override
    public Class<?> getCategory() {
        return Bonus.class;
    }

}
