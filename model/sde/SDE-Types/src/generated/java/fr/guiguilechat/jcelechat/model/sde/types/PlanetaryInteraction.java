package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction.CommandCenters;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction.ExtractorControlUnits;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction.Extractors;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction.PlanetaryLinks;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction.Processors;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction.Spaceports;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction.StorageFacilities;

public abstract class PlanetaryInteraction
    extends EveType
{
    public static final PlanetaryInteraction.MetaCat METACAT = new PlanetaryInteraction.MetaCat();

    @Override
    public IMetaCategory<PlanetaryInteraction> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<PlanetaryInteraction>
    {

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
            return Arrays.asList(Extractors.METAGROUP, CommandCenters.METAGROUP, Processors.METAGROUP, StorageFacilities.METAGROUP, Spaceports.METAGROUP, PlanetaryLinks.METAGROUP, ExtractorControlUnits.METAGROUP);
        }
    }
}
