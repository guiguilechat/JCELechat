
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class InfrastructureUpgrades
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  39;
    }

    @Override
    public Class<?> getCategory() {
        return InfrastructureUpgrades.class;
    }

}
