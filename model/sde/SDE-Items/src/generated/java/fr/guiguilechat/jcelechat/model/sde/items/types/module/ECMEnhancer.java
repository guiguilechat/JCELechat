package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class ECMEnhancer
    extends Module
{

    @Override
    public int getGroupId() {
        return  753;
    }

    @Override
    public Class<?> getGroup() {
        return ECMEnhancer.class;
    }
}
