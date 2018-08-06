package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGenericBattleships
    extends Entity
{

    @Override
    public int getGroupId() {
        return  816;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericBattleships.class;
    }
}
