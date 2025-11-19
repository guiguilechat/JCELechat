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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoadLevelModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoadPerKm;
import fr.guiguilechat.jcelechat.model.sde.attributes.LogisticalCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoadLevelModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoadPerKm;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryIndustry;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class PlanetaryLinks
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
     * Used to calculate cpu load multiplier for PI links
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpuloadlevelmodifier;
    /**
     * CPU Usage per kilometer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpuloadperkm;
    /**
     * Transport capacity (bandwidth) in m3 per hour.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int logisticalcapacity;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int powerload;
    /**
     * Power load multiplier for PI link levels
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double powerloadlevelmodifier;
    /**
     * Megawatts per kilometer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double powerloadperkm;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CpuLoad.INSTANCE, PowerLoadPerKm.INSTANCE, CpuLoadPerKm.INSTANCE, CpuLoadLevelModifier.INSTANCE, PowerLoadLevelModifier.INSTANCE, PowerLoad.INSTANCE, LogisticalCapacity.INSTANCE })));
    public static final PlanetaryLinks.MetaGroup METAGROUP = new PlanetaryLinks.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  49 :
            {
                return cpuload;
            }
            case  1635 :
            {
                return cpuloadlevelmodifier;
            }
            case  1634 :
            {
                return cpuloadperkm;
            }
            case  1631 :
            {
                return logisticalcapacity;
            }
            case  15 :
            {
                return powerload;
            }
            case  1636 :
            {
                return powerloadlevelmodifier;
            }
            case  1633 :
            {
                return powerloadperkm;
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
    public IMetaGroup<PlanetaryLinks> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PlanetaryLinks>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetaryindustry/PlanetaryLinks.yaml";
        private Map<Integer, PlanetaryLinks> cache = (null);

        @Override
        public IMetaCategory<? super PlanetaryLinks> category() {
            return PlanetaryIndustry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1036;
        }

        @Override
        public String getName() {
            return "PlanetaryLinks";
        }

        @Override
        public synchronized Map<Integer, PlanetaryLinks> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PlanetaryLinks.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, PlanetaryLinks> types;
        }
    }
}
