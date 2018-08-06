package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class StorylineMissionFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  527;
    }

    @Override
    public Class<?> getGroup() {
        return StorylineMissionFrigate.class;
    }
}
