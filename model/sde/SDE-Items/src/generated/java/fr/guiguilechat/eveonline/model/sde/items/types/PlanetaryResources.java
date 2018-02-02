package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryresources.PlanetLiquidGas;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryresources.PlanetOrganic;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryresources.PlanetSolid;

public abstract class PlanetaryResources
    extends Item
{
    /**
     * Cost multiplier per m3 volume of this commodity when importing to a planet
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ImportTaxMultiplier;
    /**
     * Export tax multiplier when exporting this commodity off a planet.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ExportTaxMultiplier;

    @Override
    public int getCategoryId() {
        return  42;
    }

    @Override
    public Class<?> getCategory() {
        return PlanetaryResources.class;
    }

    public static Map<String, ? extends PlanetaryResources> loadCategory() {
        return Stream.of(PlanetSolid.load(), PlanetOrganic.load(), PlanetLiquidGas.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
