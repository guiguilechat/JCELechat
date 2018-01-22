package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class MissionCONCORDBattlecruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  696;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCONCORDBattlecruiser.class;
    }
}
