package fr.guiguilechat.jcelechat.model.sde.items.types.sovereigntystructures;

import fr.guiguilechat.jcelechat.model.sde.items.types.SovereigntyStructures;

public class SovereigntyBlockadeUnit
    extends SovereigntyStructures
{

    @Override
    public int getGroupId() {
        return  1005;
    }

    @Override
    public Class<?> getGroup() {
        return SovereigntyBlockadeUnit.class;
    }
}
