package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class SystemScanner
    extends Module
{

    @Override
    public int getGroupId() {
        return  472;
    }

    @Override
    public Class<?> getGroup() {
        return SystemScanner.class;
    }
}
