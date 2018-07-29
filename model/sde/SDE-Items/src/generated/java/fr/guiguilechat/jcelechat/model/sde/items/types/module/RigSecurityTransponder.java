package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class RigSecurityTransponder
    extends Module
{

    @Override
    public int getGroupId() {
        return  896;
    }

    @Override
    public Class<?> getGroup() {
        return RigSecurityTransponder.class;
    }
}
