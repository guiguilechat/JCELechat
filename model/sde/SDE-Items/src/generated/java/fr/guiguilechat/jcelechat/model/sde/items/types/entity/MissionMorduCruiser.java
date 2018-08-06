package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionMorduCruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  701;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMorduCruiser.class;
    }
}
