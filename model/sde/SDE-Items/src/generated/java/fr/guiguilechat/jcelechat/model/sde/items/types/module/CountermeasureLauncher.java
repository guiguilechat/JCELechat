package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class CountermeasureLauncher
    extends Module
{

    @Override
    public int getGroupId() {
        return  308;
    }

    @Override
    public Class<?> getGroup() {
        return CountermeasureLauncher.class;
    }
}
