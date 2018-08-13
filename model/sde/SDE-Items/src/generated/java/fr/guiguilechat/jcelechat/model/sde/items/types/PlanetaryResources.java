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
    public int getCategoryId() {
        return  42;
    }

    @Override
    public MetaCategory<PlanetaryResources> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends PlanetaryResources> loadCategory() {
        return Stream.of(PlanetLiquidGasRawResource.load(), PlanetOrganicRawResource.load(), PlanetSolidRawResource.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<PlanetaryResources>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends PlanetaryResources> [] groups = new MetaGroup[] {PlanetSolidRawResource.METAGROUP, PlanetLiquidGasRawResource.METAGROUP, PlanetOrganicRawResource.METAGROUP };

        @Override
        public String getName() {
            return "PlanetaryResources";
        }

        public Collection<MetaGroup<? extends PlanetaryResources>> groups() {
            return Arrays.asList(groups);
        }
    }
}
