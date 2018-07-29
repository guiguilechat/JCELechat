package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class EnergyVampireSlayer
    extends Module
{

    @Override
    public int getGroupId() {
        return  660;
    }

    @Override
    public Class<?> getGroup() {
        return EnergyVampireSlayer.class;
    }
}
