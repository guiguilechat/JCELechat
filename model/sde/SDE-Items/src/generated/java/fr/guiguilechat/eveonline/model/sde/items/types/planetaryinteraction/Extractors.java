
package fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class Extractors
    extends PlanetaryInteraction
{

    /**
     * This type can only be found/used/created on a planet matching this type ID.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PlanetRestriction;
    /**
     * CPU load of ship
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CpuLoad;
    /**
     * The type of material harvested.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double HarvesterType;
    /**
     * Base amount (in units) of commodities extracted by an extractor pin.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double PinExtractionQuantity;
    /**
     * Base cycle time (in seconds) of an extractor pin.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(300.0D)
    public double PinCycleTime;
    /**
     * This is the radius that the depletion at this pin effects
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(10.0D)
    public double ExtractorDepletionRange;
    /**
     * This is the amount that is added to the depletion of a resource on a planet
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ExtractorDepletionRate;
    /**
     * Current load of power core
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PowerLoad;
    public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/Extractors.yaml";
    private static LinkedHashMap<String, Extractors> cache = (null);

    @Override
    public int getGroupId() {
        return  1026;
    }

    @Override
    public Class<?> getGroup() {
        return Extractors.class;
    }

    public static LinkedHashMap<String, Extractors> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Extractors.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Extractors> items;

    }

}
