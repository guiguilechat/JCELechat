package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities.AdvancedCommoditiesTier4;
import fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities.BasicCommoditiesTier1;
import fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities.RefinedCommoditiesTier2;
import fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities.SpecializedCommoditiesTier3;

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
    public Class<?> getCategory() {
        return PlanetaryCommodities.class;
    }

    public static Map<String, ? extends PlanetaryCommodities> loadCategory() {
        return Stream.of(AdvancedCommoditiesTier4 .load(), BasicCommoditiesTier1 .load(), RefinedCommoditiesTier2 .load(), SpecializedCommoditiesTier3 .load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
