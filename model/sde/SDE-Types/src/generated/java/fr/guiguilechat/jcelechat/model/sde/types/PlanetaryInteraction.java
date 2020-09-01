package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
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
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE })));
    public static final PlanetaryInteraction.MetaCat METACAT = new PlanetaryInteraction.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  4 :
            {
                return mass;
            }
            case  162 :
            {
                return radius;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

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
