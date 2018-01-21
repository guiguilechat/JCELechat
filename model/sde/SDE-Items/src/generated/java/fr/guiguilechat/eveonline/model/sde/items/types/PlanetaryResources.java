
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;

public abstract class PlanetaryResources
    extends Item
{

    /**
     * Cost multiplier per m3 volume of this commodity when importing to a planet
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ImportTaxMultiplier;
    /**
     * Export tax multiplier when exporting this commodity off a planet.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ExportTaxMultiplier;

    @Override
    public int getCategoryId() {
        return  42;
    }

    @Override
    public Class<?> getCategory() {
        return PlanetaryResources.class;
    }

}
