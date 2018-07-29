package fr.guiguilechat.jcelechat.model.sde.items.types.sovereigntystructures;

import fr.guiguilechat.jcelechat.model.sde.items.types.SovereigntyStructures;

public class DefenseBunkers
    extends SovereigntyStructures
{

    @Override
    public int getGroupId() {
        return  1004;
    }

    @Override
    public Class<?> getGroup() {
        return DefenseBunkers.class;
    }
}
