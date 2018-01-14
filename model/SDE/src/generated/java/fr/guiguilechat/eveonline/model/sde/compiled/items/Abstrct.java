
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Abstrct
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  29;
    }

    @Override
    public Class<?> getCategory() {
        return Abstrct.class;
    }

}
