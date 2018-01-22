package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

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
