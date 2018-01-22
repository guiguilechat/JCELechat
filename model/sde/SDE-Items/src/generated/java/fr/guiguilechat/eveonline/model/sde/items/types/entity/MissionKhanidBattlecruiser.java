package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class MissionKhanidBattlecruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  690;
    }

    @Override
    public Class<?> getGroup() {
        return MissionKhanidBattlecruiser.class;
    }
}
