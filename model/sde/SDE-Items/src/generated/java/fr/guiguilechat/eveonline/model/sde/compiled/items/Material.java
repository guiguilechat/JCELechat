
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Material
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  4;
    }

    @Override
    public Class<?> getCategory() {
        return Material.class;
    }

}
