package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
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
    public final static PlanetaryCommodities.MetaCat METACAT = new PlanetaryCommodities.MetaCat();

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
    public int getCategoryId() {
        return  43;
    }

    @Override
    public MetaCategory<PlanetaryCommodities> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends PlanetaryCommodities> loadCategory() {
        return Stream.of(AdvancedCommoditiesTier4 .load(), BasicCommoditiesTier1 .load(), RefinedCommoditiesTier2 .load(), SpecializedCommoditiesTier3 .load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<PlanetaryCommodities>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends PlanetaryCommodities> [] groups = new MetaGroup[] {RefinedCommoditiesTier2 .METAGROUP, SpecializedCommoditiesTier3 .METAGROUP, AdvancedCommoditiesTier4 .METAGROUP, BasicCommoditiesTier1 .METAGROUP };

        @Override
        public String getName() {
            return "PlanetaryCommodities";
        }

        public Collection<MetaGroup<? extends PlanetaryCommodities>> groups() {
            return Arrays.asList(groups);
        }
    }
}
