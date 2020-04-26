package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.planetarycommodities.AdvancedCommoditiesTier4;
import fr.guiguilechat.jcelechat.model.sde.types.planetarycommodities.BasicCommoditiesTier1;
import fr.guiguilechat.jcelechat.model.sde.types.planetarycommodities.RefinedCommoditiesTier2;
import fr.guiguilechat.jcelechat.model.sde.types.planetarycommodities.SpecializedCommoditiesTier3;

public abstract class PlanetaryCommodities
    extends EveType
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
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
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    public static final PlanetaryCommodities.MetaCat METACAT = new PlanetaryCommodities.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return Capacity;
            }
            case  1641 :
            {
                return ExportTaxMultiplier;
            }
            case  1640 :
            {
                return ImportTaxMultiplier;
            }
            case  4 :
            {
                return Mass;
            }
            case  162 :
            {
                return Radius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaCategory<PlanetaryCommodities> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<PlanetaryCommodities>
    {

        @Override
        public int getCategoryId() {
            return  43;
        }

        @Override
        public String getName() {
            return "PlanetaryCommodities";
        }

        @Override
        public Collection<IMetaGroup<? extends PlanetaryCommodities>> groups() {
            return Arrays.asList(RefinedCommoditiesTier2 .METAGROUP, SpecializedCommoditiesTier3 .METAGROUP, AdvancedCommoditiesTier4 .METAGROUP, BasicCommoditiesTier1 .METAGROUP);
        }
    }
}
