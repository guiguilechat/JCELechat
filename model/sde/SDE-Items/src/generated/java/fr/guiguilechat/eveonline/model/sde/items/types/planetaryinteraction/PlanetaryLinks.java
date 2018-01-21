
package fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class PlanetaryLinks
    extends PlanetaryInteraction
{

    /**
     * CPU load of ship
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuLoad;
    /**
     * Megawatts per kilometer
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double PowerLoadPerKm;
    /**
     * CPU Usage per kilometer
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CpuLoadPerKm;
    /**
     * Used to calculate cpu load multiplier for PI links
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CpuLoadLevelModifier;
    /**
     * Power load multiplier for PI link levels
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double PowerLoadLevelModifier;
    /**
     * Transport capacity (bandwidth) in m3 per hour.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LogisticalCapacity;
    /**
     * Current load of power core
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerLoad;
    public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/PlanetaryLinks.yaml";
    private static LinkedHashMap<String, PlanetaryLinks> cache = (null);

    @Override
    public int getGroupId() {
        return  1036;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetaryLinks.class;
    }

    public static LinkedHashMap<String, PlanetaryLinks> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PlanetaryLinks.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PlanetaryLinks> items;

    }

}
