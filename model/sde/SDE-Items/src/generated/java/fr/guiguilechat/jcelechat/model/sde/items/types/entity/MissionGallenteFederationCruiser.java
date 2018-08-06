package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGallenteFederationCruiser
    extends Entity
{

    @Override
    public int getGroupId() {
        return  678;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGallenteFederationCruiser.class;
    }
}
