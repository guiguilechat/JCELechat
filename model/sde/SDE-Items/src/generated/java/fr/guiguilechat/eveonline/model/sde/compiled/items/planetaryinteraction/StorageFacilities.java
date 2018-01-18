
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class StorageFacilities
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
     * Current load of power core
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PowerLoad;
    public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/StorageFacilities.yaml";
    private static LinkedHashMap<String, StorageFacilities> cache = (null);

    @Override
    public int getGroupId() {
        return  1029;
    }

    @Override
    public Class<?> getGroup() {
        return StorageFacilities.class;
    }

    public static LinkedHashMap<String, StorageFacilities> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StorageFacilities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StorageFacilities> items;

    }

}
