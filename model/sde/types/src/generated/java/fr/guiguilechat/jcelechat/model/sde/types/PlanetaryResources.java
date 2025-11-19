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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImportTaxMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryresources.PlanetLiquidGasRawResource;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryresources.PlanetOrganicRawResource;
import fr.guiguilechat.jcelechat.model.sde.types.planetaryresources.PlanetSolidRawResource;

public abstract class PlanetaryResources
    extends EveType
{
    /**
     * Export tax multiplier when exporting this commodity off a planet.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int exporttaxmultiplier;
    /**
     * Cost multiplier per m3 volume of this commodity when importing to a planet
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int importtaxmultiplier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ImportTaxMultiplier.INSTANCE, ExportTaxMultiplier.INSTANCE })));
    public static final PlanetaryResources.MetaCat METACAT = new PlanetaryResources.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1641 :
            {
                return exporttaxmultiplier;
            }
            case  1640 :
            {
                return importtaxmultiplier;
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
