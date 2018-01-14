
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetaryinteraction;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class Spaceports
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
     * Base importation tax (ISK per m3 of volume) for commodities imported to pin.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ImportTax;
    /**
     * Base export tax (ISK per m3 of volume) on commodities exported from a planet via this pin.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ExportTax;
    /**
     * Current load of power core
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PowerLoad;
    public final static String RESOURCE_PATH = "SDE/planetaryinteraction/Spaceports.yaml";
    private static LinkedHashMap<String, Spaceports> cache = (null);

    @Override
    public int getGroupId() {
        return  1030;
    }

    @Override
    public Class<?> getGroup() {
        return Spaceports.class;
    }

    public static LinkedHashMap<String, Spaceports> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Spaceports> items;

    }

}
