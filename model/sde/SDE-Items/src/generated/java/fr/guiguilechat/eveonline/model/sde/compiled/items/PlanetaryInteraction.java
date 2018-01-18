
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;

public abstract class PlanetaryInteraction
    extends EveItem
{


    @Override
    public int getCategoryId() {
        return  41;
    }

    @Override
    public Class<?> getCategory() {
        return PlanetaryInteraction.class;
    }

}
