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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryresources.PlanetLiquidGasRawResource;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryresources.PlanetOrganicRawResource;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryresources.PlanetSolidRawResource;

public abstract class PlanetaryResources
    extends EveType
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
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
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {fr.guiguilechat.jcelechat.model.sde.attributes.Radius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Mass.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Capacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ImportTaxMultiplier.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ExportTaxMultiplier.INSTANCE })));
    public static final PlanetaryResources.MetaCat METACAT = new PlanetaryResources.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return Capacity;
            }
            case  1641 :
            {
                return ExportTaxMultiplier;
            }
            case  1640 :
            {
                return ImportTaxMultiplier;
            }
            case  4 :
            {
                return Mass;
            }
            case  162 :
            {
                return Radius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<PlanetaryResources> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<PlanetaryResources>
    {

        @Override
        public int getCategoryId() {
            return  42;
        }

        @Override
        public String getName() {
            return "PlanetaryResources";
        }

        @Override
        public Collection<IMetaGroup<? extends PlanetaryResources>> groups() {
            return Arrays.asList(PlanetSolidRawResource.METAGROUP, PlanetLiquidGasRawResource.METAGROUP, PlanetOrganicRawResource.METAGROUP);
        }
    }
}
