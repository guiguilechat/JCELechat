package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class AntiCloakingPulse
    extends Module
{

    @Override
    public int getGroupId() {
        return  405;
    }

    @Override
    public Class<?> getGroup() {
        return AntiCloakingPulse.class;
    }
}
