package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction.CommandCenters;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction.ExtractorControlUnits;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction.Extractors;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction.PlanetaryLinks;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction.Processors;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction.Spaceports;
import fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction.StorageFacilities;

public abstract class PlanetaryInteraction
    extends Item
{
    public final static PlanetaryInteraction.MetaCat METACAT = new PlanetaryInteraction.MetaCat();

    @Override
    public int getCategoryId() {
        return  41;
    }

    @Override
    public MetaCategory<PlanetaryInteraction> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends PlanetaryInteraction> loadCategory() {
        return Stream.of(CommandCenters.load(), ExtractorControlUnits.load(), Extractors.load(), PlanetaryLinks.load(), Processors.load(), Spaceports.load(), StorageFacilities.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<PlanetaryInteraction>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends PlanetaryInteraction> [] groups = new MetaGroup[] {Extractors.METAGROUP, CommandCenters.METAGROUP, Processors.METAGROUP, StorageFacilities.METAGROUP, Spaceports.METAGROUP, PlanetaryLinks.METAGROUP, ExtractorControlUnits.METAGROUP };

        @Override
        public String getName() {
            return "PlanetaryInteraction";
        }

        public Collection<MetaGroup<? extends PlanetaryInteraction>> groups() {
            return Arrays.asList(groups);
        }
    }
}
