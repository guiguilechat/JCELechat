package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
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
    public IMetaCategory<PlanetaryInteraction> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<PlanetaryInteraction>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends PlanetaryInteraction> [] groups = new IMetaGroup[] {Extractors.METAGROUP, CommandCenters.METAGROUP, Processors.METAGROUP, StorageFacilities.METAGROUP, Spaceports.METAGROUP, PlanetaryLinks.METAGROUP, ExtractorControlUnits.METAGROUP };

        @Override
        public int getCategoryId() {
            return  41;
        }

        @Override
        public String getName() {
            return "PlanetaryInteraction";
        }

        @Override
        public Collection<IMetaGroup<? extends PlanetaryInteraction>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, PlanetaryInteraction> load() {
            HashMap<String, PlanetaryInteraction> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
