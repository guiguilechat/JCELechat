package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class MissionThukkerOther
    extends Entity
{

    @Override
    public int getGroupId() {
        return  827;
    }

    @Override
    public Class<?> getGroup() {
        return MissionThukkerOther.class;
    }
}
