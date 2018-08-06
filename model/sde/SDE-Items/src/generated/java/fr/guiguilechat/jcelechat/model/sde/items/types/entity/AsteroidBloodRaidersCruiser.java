package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidBloodRaidersCruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  555;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersCruiser.class;
    }
}
