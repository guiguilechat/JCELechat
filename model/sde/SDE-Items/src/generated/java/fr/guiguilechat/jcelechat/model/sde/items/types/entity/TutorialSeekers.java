package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class TutorialSeekers
    extends Entity
{

    @Override
    public int getGroupId() {
        return  1768;
    }

    @Override
    public Class<?> getGroup() {
        return TutorialSeekers.class;
    }
}
