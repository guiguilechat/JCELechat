package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction.CommandCenters;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction.ExtractorControlUnits;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction.Extractors;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction.PlanetaryLinks;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction.Processors;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction.Spaceports;
import fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction.StorageFacilities;

public abstract class PlanetaryInteraction
    extends Item
{

    @Override
    public int getCategoryId() {
        return  41;
    }

    @Override
    public Class<?> getCategory() {
        return PlanetaryInteraction.class;
    }

    public static Map<String, ? extends PlanetaryInteraction> loadCategory() {
        return Stream.of(Processors.load(), StorageFacilities.load(), Spaceports.load(), PlanetaryLinks.load(), Extractors.load(), CommandCenters.load(), ExtractorControlUnits.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
