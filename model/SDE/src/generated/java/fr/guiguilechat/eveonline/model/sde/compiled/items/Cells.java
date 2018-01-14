
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Cells
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  59;
    }

    @Override
    public Class<?> getCategory() {
        return Cells.class;
    }

}
