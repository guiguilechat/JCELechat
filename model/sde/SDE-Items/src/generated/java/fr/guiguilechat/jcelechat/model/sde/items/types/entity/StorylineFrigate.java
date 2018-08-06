package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class StorylineFrigate
    extends Entity
{

    @Override
    public int getGroupId() {
        return  520;
    }

    @Override
    public Class<?> getGroup() {
        return StorylineFrigate.class;
    }
}
