package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class FWInfrastructureHub
    extends Entity
{

    @Override
    public int getGroupId() {
        return  925;
    }

    @Override
    public Class<?> getGroup() {
        return FWInfrastructureHub.class;
    }
}
