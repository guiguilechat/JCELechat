package fr.guiguilechat.eveonline.model.sde.items.types.sovereigntystructures;

import fr.guiguilechat.eveonline.model.sde.items.types.SovereigntyStructures;

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
