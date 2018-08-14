package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources.PlanetLiquidGasRawResource;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources.PlanetOrganicRawResource;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources.PlanetSolidRawResource;

public abstract class PlanetaryResources
    extends Item
{
    /**
     * Export tax multiplier when exporting this commodity off a planet.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ExportTaxMultiplier;
    /**
     * Cost multiplier per m3 volume of this commodity when importing to a planet
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ImportTaxMultiplier;
    public final static PlanetaryResources.MetaCat METACAT = new PlanetaryResources.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1641 :
            {
                return ExportTaxMultiplier;
            }
            case  1640 :
            {
                return ImportTaxMultiplier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaCategory<PlanetaryResources> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<PlanetaryResources>
    {

        @Override
        public int getCategoryId() {
            return  42;
        }

        @Override
        public String getName() {
            return "PlanetaryResources";
        }

        @Override
        public Collection<IMetaGroup<? extends PlanetaryResources>> groups() {
            return Arrays.asList(PlanetSolidRawResource.METAGROUP, PlanetLiquidGasRawResource.METAGROUP, PlanetOrganicRawResource.METAGROUP);
        }
    }
}
