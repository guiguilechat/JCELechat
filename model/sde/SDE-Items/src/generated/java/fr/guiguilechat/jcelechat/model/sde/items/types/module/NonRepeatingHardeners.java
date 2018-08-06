package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class NonRepeatingHardeners
    extends Module
{

    @Override
    public int getGroupId() {
        return  1894;
    }

    @Override
    public Class<?> getGroup() {
        return NonRepeatingHardeners.class;
    }
}
