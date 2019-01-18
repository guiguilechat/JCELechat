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
import fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities.AdvancedCommoditiesTier4;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities.BasicCommoditiesTier1;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities.RefinedCommoditiesTier2;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities.SpecializedCommoditiesTier3;

public abstract class PlanetaryCommodities
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
