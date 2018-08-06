package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class CapturePoint
    extends Entity
{

    @Override
    public int getGroupId() {
        return  922;
    }

    @Override
    public Class<?> getGroup() {
        return CapturePoint.class;
    }
}
