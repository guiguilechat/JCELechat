
package fr.guiguilechat.eveonline.model.sde.compiled.items.sovereigntystructures;

import fr.guiguilechat.eveonline.model.sde.compiled.items.SovereigntyStructures;

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
