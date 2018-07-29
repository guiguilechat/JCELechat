package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class MiningEnhancer
    extends Module
{

    @Override
    public int getGroupId() {
        return  901;
    }

    @Override
    public Class<?> getGroup() {
        return MiningEnhancer.class;
    }
}
