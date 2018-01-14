
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetaryinteraction;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryInteraction;
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
    @DefaultValue(0.0D)
    public double CpuLoad;
    /**
     * Megawatts per kilometer
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PowerLoadPerKm;
    /**
     * CPU Usage per kilometer
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CpuLoadPerKm;
    /**
     * Used to calculate cpu load multiplier for PI links
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CpuLoadLevelModifier;
    /**
     * Power load multiplier for PI link levels
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PowerLoadLevelModifier;
    /**
     * Transport capacity (bandwidth) in m3 per hour.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double LogisticalCapacity;
    /**
     * Current load of power core
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PowerLoad;
    public final static String RESOURCE_PATH = "SDE/planetaryinteraction/PlanetaryLinks.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PlanetaryLinks> items;

    }

}
