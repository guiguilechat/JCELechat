package fr.guiguilechat.jcelechat.model.sde.items.types.deployable;

import fr.guiguilechat.jcelechat.model.sde.items.types.Deployable;

public class MobileJumpDisruptor
    extends Deployable
{

    @Override
    public int getGroupId() {
        return  1149;
    }

    @Override
    public Class<?> getGroup() {
        return MobileJumpDisruptor.class;
    }
}
