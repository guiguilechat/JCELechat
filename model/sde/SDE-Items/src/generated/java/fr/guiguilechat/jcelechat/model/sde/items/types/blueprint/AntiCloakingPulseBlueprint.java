package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class AntiCloakingPulseBlueprint
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  410;
    }

    @Override
    public Class<?> getGroup() {
        return AntiCloakingPulseBlueprint.class;
    }
}
