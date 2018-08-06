package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionMorduBattleship
    extends Entity
{

    @Override
    public int getGroupId() {
        return  703;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMorduBattleship.class;
    }
}
