
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;

public class EventShips
    extends Entity
{


    @Override
    public int getGroupId() {
        return  1895;
    }

    @Override
    public Class<?> getGroup() {
        return EventShips.class;
    }

}
