
package fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class ExtractorControlUnits
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
     * Base amount (in units) of commodities extracted by an extractor pin.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double PinExtractionQuantity;
    /**
     * CPU cost of extractor head
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(110.0D)
    public double EcuExtractorHeadCPU;
    /**
     * Power cost for a extractor head
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(500.0D)
    public double EcuExtractorHeadPower;
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
    public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/ExtractorControlUnits.yaml";
    private static LinkedHashMap<String, ExtractorControlUnits> cache = (null);

    @Override
    public int getGroupId() {
        return  1063;
    }

    @Override
    public Class<?> getGroup() {
        return ExtractorControlUnits.class;
    }

    public static LinkedHashMap<String, ExtractorControlUnits> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ExtractorControlUnits.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ExtractorControlUnits> items;

    }

}
