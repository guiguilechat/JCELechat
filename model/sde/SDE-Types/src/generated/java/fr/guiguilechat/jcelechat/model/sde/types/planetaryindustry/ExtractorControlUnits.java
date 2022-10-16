package fr.guiguilechat.jcelechat.model.sde.types.planetaryindustry;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.EcuExtractorHeadCPU;
import fr.guiguilechat.jcelechat.model.sde.attributes.EcuExtractorHeadPower;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExtractorDepletionRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExtractorDepletionRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.PinCycleTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.PinExtractionQuantity;
import fr.guiguilechat.jcelechat.model.sde.attributes.PlanetRestriction;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryIndustry;
import org.yaml.snakeyaml.Yaml;

public class ExtractorControlUnits
    extends PlanetaryIndustry
{
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cpuload;
    /**
     * CPU cost of extractor head
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(110)
    public int ecuextractorheadcpu;
    /**
     * Power cost for a extractor head
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(500)
    public int ecuextractorheadpower;
    /**
     * This is the radius that the depletion at this pin effects
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int extractordepletionrange;
    /**
     * This is the amount that is added to the depletion of a resource on a planet
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int extractordepletionrate;
    /**
     * Base cycle time (in seconds) of an extractor pin.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300)
    public int pincycletime;
    /**
     * Base amount (in units) of commodities extracted by an extractor pin.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int pinextractionquantity;
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int planetrestriction;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int powerload;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {PlanetRestriction.INSTANCE, CpuLoad.INSTANCE, Radius.INSTANCE, Capacity.INSTANCE, PinExtractionQuantity.INSTANCE, EcuExtractorHeadCPU.INSTANCE, PinCycleTime.INSTANCE, EcuExtractorHeadPower.INSTANCE, ExtractorDepletionRange.INSTANCE, ExtractorDepletionRate.INSTANCE, PowerLoad.INSTANCE })));
    public static final ExtractorControlUnits.MetaGroup METAGROUP = new ExtractorControlUnits.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  49 :
            {
                return cpuload;
            }
            case  1690 :
            {
                return ecuextractorheadcpu;
            }
            case  1691 :
            {
                return ecuextractorheadpower;
            }
            case  1644 :
            {
                return extractordepletionrange;
            }
            case  1645 :
            {
                return extractordepletionrate;
            }
            case  1643 :
            {
                return pincycletime;
            }
            case  1642 :
            {
                return pinextractionquantity;
            }
            case  1632 :
            {
                return planetrestriction;
            }
            case  15 :
            {
                return powerload;
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
    public IMetaGroup<ExtractorControlUnits> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ExtractorControlUnits>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetaryindustry/ExtractorControlUnits.yaml";
        private Map<String, ExtractorControlUnits> cache = (null);

        @Override
        public IMetaCategory<? super ExtractorControlUnits> category() {
            return PlanetaryIndustry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1063;
        }

        @Override
        public String getName() {
            return "ExtractorControlUnits";
        }

        @Override
        public synchronized Map<String, ExtractorControlUnits> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ExtractorControlUnits.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ExtractorControlUnits> types;
        }
    }
}
