
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;

public abstract class PlanetaryResources
    extends EveItem
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
