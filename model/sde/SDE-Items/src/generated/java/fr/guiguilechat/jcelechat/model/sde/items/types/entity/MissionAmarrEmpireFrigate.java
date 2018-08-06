package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionAmarrEmpireFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  665;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireFrigate.class;
    }
}
