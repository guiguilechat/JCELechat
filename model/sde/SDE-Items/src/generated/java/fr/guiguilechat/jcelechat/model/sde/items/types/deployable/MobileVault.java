package fr.guiguilechat.jcelechat.model.sde.items.types.deployable;

import fr.guiguilechat.jcelechat.model.sde.items.types.Deployable;

public class MobileVault
    extends Deployable
{

    @Override
    public int getGroupId() {
        return  1297;
    }

    @Override
    public Class<?> getGroup() {
        return MobileVault.class;
    }
}
