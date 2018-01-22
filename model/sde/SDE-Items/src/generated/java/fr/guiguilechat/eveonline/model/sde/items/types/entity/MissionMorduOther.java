package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class MissionMorduOther
    extends Entity
{

    @Override
    public int getGroupId() {
        return  704;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMorduOther.class;
    }
}
