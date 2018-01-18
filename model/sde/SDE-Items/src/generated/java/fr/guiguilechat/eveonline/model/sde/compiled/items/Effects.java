
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Effects
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  53;
    }

    @Override
    public Class<?> getCategory() {
        return Effects.class;
    }

}
