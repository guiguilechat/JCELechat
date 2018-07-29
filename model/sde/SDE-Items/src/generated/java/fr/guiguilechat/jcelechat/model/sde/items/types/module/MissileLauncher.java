package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class MissileLauncher
    extends Module
{

    @Override
    public int getGroupId() {
        return  56;
    }

    @Override
    public Class<?> getGroup() {
        return MissileLauncher.class;
    }
}
