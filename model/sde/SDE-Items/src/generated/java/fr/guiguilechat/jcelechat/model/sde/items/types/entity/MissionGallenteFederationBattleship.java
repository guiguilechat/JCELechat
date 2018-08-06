package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGallenteFederationBattleship
    extends Entity
{

    @Override
    public int getGroupId() {
        return  680;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGallenteFederationBattleship.class;
    }
}
