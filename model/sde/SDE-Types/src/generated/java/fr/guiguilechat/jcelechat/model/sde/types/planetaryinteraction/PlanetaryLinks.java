package fr.guiguilechat.jcelechat.model.sde.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class PlanetaryLinks
    extends PlanetaryInteraction
{
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuLoad;
    /**
     * Used to calculate cpu load multiplier for PI links
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CpuLoadLevelModifier;
    /**
     * CPU Usage per kilometer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CpuLoadPerKm;
    /**
     * Transport capacity (bandwidth) in m3 per hour.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LogisticalCapacity;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerLoad;
    /**
     * Power load multiplier for PI link levels
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double PowerLoadLevelModifier;
    /**
     * Megawatts per kilometer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double PowerLoadPerKm;
    public static final PlanetaryLinks.MetaGroup METAGROUP = new PlanetaryLinks.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  49 :
            {
                return CpuLoad;
            }
            case  1635 :
            {
                return CpuLoadLevelModifier;
            }
            case  1634 :
            {
                return CpuLoadPerKm;
            }
            case  1631 :
            {
                return LogisticalCapacity;
            }
            case  15 :
            {
                return PowerLoad;
            }
            case  1636 :
            {
                return PowerLoadLevelModifier;
            }
            case  1633 :
            {
                return PowerLoadPerKm;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<PlanetaryLinks> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PlanetaryLinks>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetaryinteraction/PlanetaryLinks.yaml";
        private Map<String, PlanetaryLinks> cache = (null);

        @Override
        public IMetaCategory<? super PlanetaryLinks> category() {
            return PlanetaryInteraction.METACAT;
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
        public synchronized Map<String, PlanetaryLinks> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PlanetaryLinks.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PlanetaryLinks> types;
        }
    }
}
