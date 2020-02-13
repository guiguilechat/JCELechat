package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
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
    public static final PlanetaryCommodities.MetaCat METACAT = new PlanetaryCommodities.MetaCat();

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
