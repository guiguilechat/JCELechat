
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class SpecialEditionAssets
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  63;
    }

    @Override
    public Class<?> getCategory() {
        return SpecialEditionAssets.class;
    }

}
