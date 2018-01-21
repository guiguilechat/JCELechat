
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import fr.guiguilechat.eveonline.model.sde.items.types.Entity;

public class ProtectiveSentryGun
    extends Entity
{


    @Override
    public int getGroupId() {
        return  180;
    }

    @Override
    public Class<?> getGroup() {
        return ProtectiveSentryGun.class;
    }

}
