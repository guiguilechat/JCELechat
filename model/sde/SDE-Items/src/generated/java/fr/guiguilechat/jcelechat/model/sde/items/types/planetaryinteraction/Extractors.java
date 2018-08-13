package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class Extractors
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
     * This is the radius that the depletion at this pin effects
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int ExtractorDepletionRange;
    /**
     * This is the amount that is added to the depletion of a resource on a planet
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ExtractorDepletionRate;
    /**
     * The type of material harvested.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HarvesterType;
    /**
     * Base cycle time (in seconds) of an extractor pin.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300)
    public int PinCycleTime;
    /**
     * Base amount (in units) of commodities extracted by an extractor pin.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int PinExtractionQuantity;
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PlanetRestriction;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerLoad;
    public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/Extractors.yaml";
    private static Map<String, Extractors> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  49 :
            {
                return CpuLoad;
            }
            case  1644 :
            {
                return ExtractorDepletionRange;
            }
            case  1645 :
            {
                return ExtractorDepletionRate;
            }
            case  709 :
            {
                return HarvesterType;
            }
            case  1643 :
            {
                return PinCycleTime;
            }
            case  1642 :
            {
                return PinExtractionQuantity;
            }
            case  1632 :
            {
                return PlanetRestriction;
            }
            case  15 :
            {
                return PowerLoad;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1026;
    }

    @Override
    public Class<?> getGroup() {
        return Extractors.class;
    }

    public static synchronized Map<String, Extractors> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Extractors.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Extractors> items;
    }
}
