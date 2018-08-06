package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class MobileSentryGun
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  336;
    }

    @Override
    public Class<?> getGroup() {
        return MobileSentryGun.class;
    }
}
