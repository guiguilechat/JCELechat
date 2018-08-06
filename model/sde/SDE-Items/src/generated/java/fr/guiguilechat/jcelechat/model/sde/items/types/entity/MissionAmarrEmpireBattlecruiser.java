package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionAmarrEmpireBattlecruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  666;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireBattlecruiser.class;
    }
}
