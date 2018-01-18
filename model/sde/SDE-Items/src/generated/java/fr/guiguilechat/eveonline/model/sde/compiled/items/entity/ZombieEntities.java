
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;

public class ZombieEntities
    extends Entity
{


    @Override
    public int getGroupId() {
        return  934;
    }

    @Override
    public Class<?> getGroup() {
        return ZombieEntities.class;
    }

}
