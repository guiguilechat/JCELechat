package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionMorduDestroyer
    extends Entity
{

    @Override
    public int getGroupId() {
        return  700;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMorduDestroyer.class;
    }
}
