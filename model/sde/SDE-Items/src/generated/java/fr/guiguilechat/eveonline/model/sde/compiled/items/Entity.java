
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class Entity
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  11;
    }

    @Override
    public Class<?> getCategory() {
        return Entity.class;
    }

}
