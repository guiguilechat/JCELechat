package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class StorylineBattleship
    extends Entity
{

    @Override
    public int getGroupId() {
        return  523;
    }

    @Override
    public Class<?> getGroup() {
        return StorylineBattleship.class;
    }
}
